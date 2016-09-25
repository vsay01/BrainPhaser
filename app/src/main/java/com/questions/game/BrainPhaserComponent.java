package com.questions.game;

import com.questions.game.activities.aboutscreen.AboutActivity;
import com.questions.game.activities.createuser.CreateUserActivity;
import com.questions.game.activities.login.LoginActivity;
import com.questions.game.activities.main.MainActivity;
import com.questions.game.activities.main.ProxyActivity;
import com.questions.game.activities.playchallenge.AnswerFragment;
import com.questions.game.activities.playchallenge.ChallengeActivity;
import com.questions.game.activities.playchallenge.multiplechoice.ButtonViewState;
import com.questions.game.activities.playchallenge.multiplechoice.MultipleChoiceFragment;
import com.questions.game.activities.playchallenge.selfcheck.SelfTestFragment;
import com.questions.game.activities.playchallenge.text.TextFragment;
import com.questions.game.activities.selectcategory.SelectCategoryPage;
import com.questions.game.activities.selectuser.UserAdapter;
import com.questions.game.activities.selectuser.UserSelectionActivity;
import com.questions.game.activities.statistics.StatisticsActivity;
import com.questions.game.activities.usersettings.SettingsActivity;
import com.questions.game.logic.UserLogicFactory;
import com.questions.game.logic.fileimport.bpc.BPCWrite;

/**
 * Created by funkv on 06.03.2016.
 *
 * App Component that defines injection targets for DI.
 */
public interface BrainPhaserComponent {
    void inject(MainActivity mainActivity);
    void inject(ProxyActivity activity);
    void inject(ChallengeActivity challengeActivity);
    void inject(MultipleChoiceFragment questionFragment);
    void inject(TextFragment textFragment);
    void inject(SelfTestFragment selfTestFragment);
    void inject(CreateUserActivity createUserActivity);
    void inject(UserAdapter userAdapter);
    void inject(UserSelectionActivity activity);
    void inject(StatisticsActivity activity);
    void inject(SettingsActivity activity);
    void inject(LoginActivity activity);
    void inject(AboutActivity activity);

    void inject(SelectCategoryPage selectCategoryPage);
    void inject(AnswerFragment answerFragment);

    void inject(BPCWrite bpcWrite);
    void inject(ButtonViewState state);

    void inject(UserLogicFactory f);
}
