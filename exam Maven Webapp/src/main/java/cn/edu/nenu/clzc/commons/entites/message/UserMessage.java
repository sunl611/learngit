package cn.edu.nenu.clzc.commons.entites.message;

import java.util.Date;

public class UserMessage {
    private String id;

    private String messageContent;

    private Date messageTime;

    private String userInfoId;

    private String relativeMessageId;

    private String messageIsDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getRelativeMessageId() {
        return relativeMessageId;
    }

    public void setRelativeMessageId(String relativeMessageId) {
        this.relativeMessageId = relativeMessageId;
    }

    public String getMessageIsDelete() {
        return messageIsDelete;
    }

    public void setMessageIsDelete(String messageIsDelete) {
        this.messageIsDelete = messageIsDelete;
    }
}