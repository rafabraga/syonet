package br.com.rafael.syonet.util;

import java.lang.reflect.ParameterizedType;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Utility class for unit tests.
 *
 * @param <C> the class that will be tested.
 * @author Rafael Braga
 */
public abstract class AbstractBaseUnitTest<C> {

	/**
	 * The test class.
	 */
	@InjectMocks
	protected C testClass = Mockito.spy(this.getClazz());

	/**
	 * Initialize the mocks before each test.
	 */
	@Before
	public void setUpBefore() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Search for the class that is running.
	 *
	 * @return the running {@link Class}.
	 */
	@SuppressWarnings("unchecked")
	private Class<C> getClazz() {
		final ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<C>) paramType.getActualTypeArguments()[0];
	}

}
