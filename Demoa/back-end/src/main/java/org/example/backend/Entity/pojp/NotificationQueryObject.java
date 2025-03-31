package org.example.backend.Entity.pojp;

import lombok.Data;

@Data
public class NotificationQueryObject {
    private Integer id,type;
    private Boolean isRead;
    private String title,content;
    private Data createTime;

    public NotificationQueryObject() {

    }
}
