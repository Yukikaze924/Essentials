package com.yukikaze.essentials.models;

import java.util.Date;

public class PlayerTable {
    private String uuid;
    private String name;
    private String identity;
    private Date birthday;
    private Date lastInGame;

    public PlayerTable(String uuid, String name, String identity, Date birthday, Date lastInGame) {
        this.uuid = uuid;
        this.name = name;
        this.identity = identity;
        this.birthday = birthday;
        this.lastInGame = lastInGame;
    }

    public String getPlayerUUID() {
        return uuid;
    }

    public String getPlayerName() {
        return name;
    }

    public String getPlayerIdentity() {
        return identity;
    }

    public void setPlayerIdentity() {
        this.identity = identity;
    }

    public Date getBirthday() { return birthday; }
    public void setBirthday() {
        this.birthday = birthday;
    }
    public Date getLastInGame() {
        return lastInGame;
    }
    public void setLastInGame(Date lastInGame) {
        this.lastInGame = lastInGame;
    }
}
