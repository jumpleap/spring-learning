////////////////////////////////////////////
// 这里实现标签页的切换
////////////////////////////////////////////

function initSwitchTab() {
    // 1. 先获取到相关的元素(标签页的按钮, 会话列表, 好友列表)
    let tabSession = document.querySelector('.tab .tab-session');
    let tabFriend = document.querySelector('.tab .tab-friend');
    // querySelectorAll 可以同时选中多个元素. 得到的结果是个数组
    // [0] 就是会话列表
    // [1] 就是好友列表
    let lists = document.querySelectorAll('.list');
    // 2. 针对标签页按钮, 注册点击事件.
    //    如果是点击 会话标签按钮, 就把会话标签按钮的背景图片进行设置.
    //    同时把会话列表显示出来, 把好友列表隐藏
    //    如果是点击 好友标签按钮, 就把好友标签按钮的背景图片进行设置.
    //    同时把好友列表显示出来, 把会话列表进行隐藏
    tabSession.onclick = function() {
        // a) 设置图标
        tabSession.style.backgroundImage = 'url(img/对话.png)';
        tabFriend.style.backgroundImage = 'url(img/用户2.png)';
        // b) 让会话列表显示出来, 让好友列表进行隐藏
        lists[0].classList = 'list';
        lists[1].classList = 'list hide';
    }

    tabFriend.onclick = function() {
        // a) 设置图标
        tabSession.style.backgroundImage = 'url(img/对话2.png)';
        tabFriend.style.backgroundImage = 'url(img/用户.png)'
        // b) 让好友列表显示, 让会话列表隐藏
        lists[0].classList = 'list hide';
        lists[1].classList = 'list';
    }
}

initSwitchTab();

/////////////////////////////////////////////////////
// 从服务器获取到用户登录数据
/////////////////////////////////////////////////////

function getUserInfo() {
    $.ajax({
        type: 'get',
        url: 'userInfo',
        success: function(body) {
            // 从服务器获取到数据.
            // 校验结果是否有效.
            if (body.userId && body.userId > 0) {
                // 如果结果有效, 则把用户名显示到界面上.
                // 同时也可以记录 userId 到 html 标签的属性中. (以备后用)
                let userDiv = document.querySelector('.main .left .user');
                userDiv.innerHTML = body.username;
                userDiv.setAttribute("user-id", body.userId);
            } else {
                // 如果结果无效, 当前未登录! 则跳转到登录页面.
                alert("当前用户未登录!");
                location.assign('login.html');
            }
        }
    });
}

getUserInfo();


function getFriendList() {
    $.ajax({
        type: 'get',
        url: 'friendList',
        success: function(body) {
            // 1. 先把之前的好友列表的内容, 给清空
            let friendListUL = document.querySelector('#friend-list');
            friendListUL.innerHTML = '';
            // 2. 遍历 body, 把服务器响应的结果, 加回到当前的 friend-list 中.
            for (let friend of body) {
                let li = document.createElement('li');
                li.innerHTML = '<h4>' + friend.friendName + '</h4>';
                // 此处把 friendId 也记录下来, 以备后用.
                // 把 friendId 作为一个 html 的自定义属性, 加到 li 标签上就行了.
                li.setAttribute('friend-id', friend.friendId);
                friendListUL.appendChild(li);

                // 每个 li 标签, 就对应界面上的一个好友的选项. 给这个 li 加上点击事件的处理.
                li.onclick = function() {
                    // 参数表示区分了当前用户点击的是哪个好友.
                    clickFriend(friend);
                }
            }
        },
        error: function() {
            console.log('获取好友列表失败!');
        }
    });
}

getFriendList();

function getSessionList() {
    $.ajax({
        type: 'get',
        url: 'sessionList',
        success: function(body) {
            // 1. 清空之前的会话列表
            let sessionListUL = document.querySelector('#session-list');
            sessionListUL.innerHTML = '';
            // 2. 遍历响应的数组, 针对结果来构造页面
            for (let session of body) {
                // 针对 lastMessage 的长度进行截断处理
                if (session.lastMessage.length > 10) {
                    session.lastMessage = session.lastMessage.substring(0, 10) + '...';
                }

                let li = document.createElement('li');
                // 把会话 id 保存到 li 标签的自定义属性中.
                li.setAttribute('message-session-id', session.sessionId);
                li.innerHTML = '<h3>' + session.friends[0].friendName + '</h3>'
                    + '<p>' + session.lastMessage + '</p>';
                sessionListUL.appendChild(li);

                // 给 li 标签新增点击事件
                li.onclick = function() {
                    // 这个写法, 就能保证, 点击哪个 li 标签
                    // 此处对应的 clickSession 函数的参数就能拿到哪个 li 标签.
                    clickSession(li);
                }
            }
        }
    });
}

getSessionList();

function clickSession(currentLi) {
    // 1. 设置高亮
    let allLis = document.querySelectorAll('#session-list>li');
    activeSession(allLis, currentLi);
    // 2. 获取指定会话的历史消息 TODO
    let sessionId = currentLi.getAttribute("message-session-id");
    getHistoryMessage(sessionId);
}

function activeSession(allLis, currentLi) {
    // 这里的循环遍历, 更主要的目的是取消未被选中的 li 标签的 className
    for (let li of allLis) {
        if (li == currentLi) {
            li.className = 'selected';
        } else {
            li.className = '';
        }
    }
}

// 这个函数负责获取指定会话的历史消息.
function getHistoryMessage(sessionId) {
}

// 点击好友列表项, 触发的函数
function clickFriend(friend) {
    // 1. 先判定一下当前这个好友是否有对应的会话.
    //    使用一个单独的函数来实现. 这个函数参数是用户的名字. 返回值是一个 li 标签. 找到了就是返回了对应会话列表里的 li; 如果没找到, 返回 null
    let sessionLi = findSessionByName(friend.friendName);
    let sessionListUL = document.querySelector('#session-list');
    if (sessionLi) {
        // 2. 如果存在匹配的结果, 就把这个会话设置成选中状态, 获取历史消息, 并且置顶.
        //    insertBefore 把这个找到的 li 标签放到最前面去.
        sessionListUL.insertBefore(sessionLi, sessionListUL.children[0]);
        //    此处设置会话选中状态, 获取历史消息, 这俩功能其实在上面的 clickSession 中已经有了.
        //    此处直接调用 clickSession 即可
        //    clickSession(sessionLi);
        //    或者还可以模拟一下点击操作.
        sessionLi.click();
    } else {
        // 3. 如果不存在匹配的结果, 就创建个新会话(创建 li 标签 + 通知服务器)
        sessionLi = document.createElement('li');
        //    构造 li 标签内容. 由于新会话没有 "最后一条消息", p 标签内容就设为空即可
        sessionLi.innerHTML = '<h3>' + friend.friendName + '</h3>' + '<p></p>';
        //    把标签进行置顶
        sessionListUL.insertBefore(sessionLi, sessionListUL.children[0]);
        sessionLi.onclick = function() {
            clickSession(sessionLi);
        }
        sessionLi.click();
        //     发送消息给服务器, 告诉服务器当前新创建的会话是啥样的.
        createSession(friend.friendId, sessionLi);
    }
    // 4. 还需要把标签页给切换到 会话列表.
    //    实现方式很容易, 只要找到会话列表标签页按钮, 模拟一个点击操作即可.
    let tabSession = document.querySelector('.tab .tab-session');
    tabSession.click();
}


function findSessionByName(username) {
    // 先获取到会话列表中所有的 li 标签
    // 然后依次遍历, 看看这些 li 标签谁的名字和要查找的名字一致.
    let sessionLis = document.querySelectorAll('#session-list>li');
    for (let sessionLi of sessionLis) {
        // 获取到该 li 标签里的 h3 标签, 进一步得到名字
        let h3 = sessionLi.querySelector('h3');
        if (h3.innerHTML == username) {
            return sessionLi;
        }
    }
    return null;
}


// friendId 是构造 HTTP 请求时必备的信息
function createSession(friendId, sessionLi) {
    $.ajax({
        type: 'post',
        url: 'session?toUserId=' + friendId,
        success: function(body) {
            console.log("会话创建成功! sessionId = " + body.sessionId);
            sessionLi.setAttribute('message-session-id', body.sessionId);
        },
        error: function() {
            console.log('会话创建失败!');
        }
    });
}