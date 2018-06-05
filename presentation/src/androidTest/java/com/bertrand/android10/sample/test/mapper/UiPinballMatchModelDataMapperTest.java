
package com.bertrand.android10.sample.test.mapper;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.presentation.mapper.UserModelDataMapper;
import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class UiPinballMatchModelDataMapperTest extends TestCase {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULL_NAME = "Tony Stark";

  private UserModelDataMapper userModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    userModelDataMapper = new UserModelDataMapper();
  }

  public void testTransformUser() {
    DomainPinballMatchModel domainPinballMatchModel = createFakeUser();
    UiPinballMatchModel pinballMatchModel = userModelDataMapper.transform(domainPinballMatchModel);

    assertThat(pinballMatchModel, is(instanceOf(UiPinballMatchModel.class)));
    assertThat(pinballMatchModel.getPinballMatch(), is(FAKE_FULL_NAME));
  }

  public void testTransformUserCollection() {
    DomainPinballMatchModel mockDomainPinballMatchModelOne = mock(DomainPinballMatchModel.class);
    DomainPinballMatchModel mockDomainPinballMatchModelTwo = mock(DomainPinballMatchModel.class);

    List<DomainPinballMatchModel> domainPinballMatchModelList = new ArrayList<DomainPinballMatchModel>(5);
    domainPinballMatchModelList.add(mockDomainPinballMatchModelOne);
    domainPinballMatchModelList.add(mockDomainPinballMatchModelTwo);

    Collection<UiPinballMatchModel> pinballMatchModelList = userModelDataMapper.transform(domainPinballMatchModelList);

    assertThat(pinballMatchModelList.toArray()[0], is(instanceOf(UiPinballMatchModel.class)));
    assertThat(pinballMatchModelList.toArray()[1], is(instanceOf(UiPinballMatchModel.class)));
    assertThat(pinballMatchModelList.size(), is(2));
  }

  private DomainPinballMatchModel createFakeUser() {
    DomainPinballMatchModel domainPinballMatchModel = new DomainPinballMatchModel();
    domainPinballMatchModel.setPinballMatch(FAKE_FULL_NAME);

    return domainPinballMatchModel;
  }
}
