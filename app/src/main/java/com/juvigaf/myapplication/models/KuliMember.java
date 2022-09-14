package com.juvigaf.myapplication.models;

import java.util.List;

public class KuliMember {

    private int teamId;
    private List<User>users;
    private int count;

    public KuliMember(Integer teamId, List<User> users, int count) {
        this.teamId = teamId;
        this.users = users;
        this.count = count;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
