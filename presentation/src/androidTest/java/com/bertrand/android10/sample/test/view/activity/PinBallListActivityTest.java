
package com.bertrand.android10.sample.test.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.view.activity.PinBallListActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PinBallListActivityTest extends ActivityInstrumentationTestCase2<PinBallListActivity> {

  private PinBallListActivity userListActivity;

  public PinBallListActivityTest() {
    super(PinBallListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    userListActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsUserListFragment() {
    Fragment userListFragment =
        userListActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    assertThat(userListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.userListActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("Pinball Match List"));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        PinBallListActivity.getCallingIntent(getInstrumentation().getTargetContext());

    return intentLaunchActivity;
  }
}
