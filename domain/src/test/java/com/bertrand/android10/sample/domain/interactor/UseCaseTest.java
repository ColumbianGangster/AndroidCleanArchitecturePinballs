
package com.bertrand.android10.sample.domain.interactor;

import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest {

  private UseCaseTestClass useCase;

  private TestDisposableObserver<Object> testObserver;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    this.useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
    this.testObserver = new TestDisposableObserver<>();
    given(mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
  }

  @Test
  public void testBuildUseCaseObservableReturnCorrectResult() {
    useCase.execute(testObserver);

    assertThat(testObserver.valuesCount).isZero();
  }

  @Test
  public void testSubscriptionWhenExecutingUseCase() {
    useCase.execute(testObserver);
    useCase.dispose();

    assertThat(testObserver.isDisposed()).isTrue();
  }

  @Test
  public void testShouldFailWhenExecuteWithNullObserver() {
    expectedException.expect(IllegalStateException.class);
    useCase.execute(null);
  }

  private static class UseCaseTestClass extends UseCase<Object> {

    UseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
      super(threadExecutor, postExecutionThread);
    }

    @Override Observable<Object> buildUseCaseObservable() {
      return Observable.empty();
    }

    @Override
    public void execute(DisposableObserver<Object> observer) {
      super.execute(observer);
    }
  }

  private static class TestDisposableObserver<T> extends DisposableObserver<T> {
    private int valuesCount = 0;

    @Override public void onNext(T value) {
      valuesCount++;
    }

    @Override public void onError(Throwable e) {
      // no-op by default.
    }

    @Override public void onComplete() {
      // no-op by default.
    }
  }

  private static class Params {
    private static Params EMPTY = new Params();
    private Params() {}
  }
}
