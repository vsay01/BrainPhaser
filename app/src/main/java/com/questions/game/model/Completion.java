package com.questions.game.model;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "COMPLETION".
 */
public class Completion {

    private Long id;
    private Integer stage;
    private java.util.Date lastCompleted;
    private long userId;
    private long challengeId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CompletionDao myDao;

    private Challenge challenge;
    private Long challenge__resolvedKey;


    public Completion() {
    }

    public Completion(Long id) {
        this.id = id;
    }

    public Completion(Long id, Integer stage, java.util.Date lastCompleted, long userId, long challengeId) {
        this.id = id;
        this.stage = stage;
        this.lastCompleted = lastCompleted;
        this.userId = userId;
        this.challengeId = challengeId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCompletionDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public java.util.Date getLastCompleted() {
        return lastCompleted;
    }

    public void setLastCompleted(java.util.Date lastCompleted) {
        this.lastCompleted = lastCompleted;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }

    /** To-one relationship, resolved on first access. */
    public Challenge getChallenge() {
        long __key = this.challengeId;
        if (challenge__resolvedKey == null || !challenge__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChallengeDao targetDao = daoSession.getChallengeDao();
            Challenge challengeNew = targetDao.load(__key);
            synchronized (this) {
                challenge = challengeNew;
            	challenge__resolvedKey = __key;
            }
        }
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        if (challenge == null) {
            throw new DaoException("To-one property 'challengeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.challenge = challenge;
            challengeId = challenge.getId();
            challenge__resolvedKey = challengeId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
