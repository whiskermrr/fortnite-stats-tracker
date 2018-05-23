package com.example.mrr.fortnitetracker;


import com.example.mrr.fortnitetracker.model.UserProfileModel;
import com.example.mrr.fortnitetracker.view.stats.StatsContracts;
import com.example.mrr.fortnitetracker.view.stats.StatsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class StatsPresenterTest {


    @Mock
    private StatsContracts.View view;

    @Mock
    private StatsContracts.Interactor interactor;

    private static final String FORTNITE_PLATFORM = "pc";
    private static final String FORTNITE_USERNAME = "whiskermrr";


    // new mock is created for each new test
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataAndLoadItToView() {
        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setAccountId("1231-1231-1231");

        Mockito.when(interactor.getProfile(FORTNITE_PLATFORM, FORTNITE_USERNAME))
                .thenReturn(Observable.just(userProfileModel));

        StatsPresenter statsPresenter = new StatsPresenter(this.view, this.interactor);
        statsPresenter.getUserStats(FORTNITE_PLATFORM, FORTNITE_USERNAME);

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgress();
        inOrder.verify(view, times(1)).onSuccess(userProfileModel);
        inOrder.verify(view, times(1)).hideProgress();
    }

    @Test
    public void fetchInvalidDataAndPassErrorToView() {
        UserProfileModel userProfileModel = new UserProfileModel();

        Mockito.when(interactor.getProfile(FORTNITE_PLATFORM, FORTNITE_USERNAME))
                .thenReturn(Observable.just(userProfileModel));

        StatsPresenter statsPresenter = new StatsPresenter(view, interactor);
        statsPresenter.getUserStats(FORTNITE_PLATFORM, FORTNITE_USERNAME);

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgress();
        inOrder.verify(view, times(1)).onFailure("User not found.");
        inOrder.verify(view, times(1)).hideProgress();
    }

}
