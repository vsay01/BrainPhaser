package com.questions.game.logic;

import org.mockito.Mockito;

import com.questions.game.model.User;

/**
 * Created by funkv on 07.03.2016.
 */
public class MockTestUserLogicFactory extends UserLogicFactory {
    @Override
    public DueChallengeLogic createDueChallengeLogic(User user) {
        return Mockito.mock(DueChallengeLogic.class);
    }
}
