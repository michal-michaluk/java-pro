package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import workshop.java.fundamentals.basics.bizz.Database;
import workshop.java.fundamentals.basics.bizz.RemoteService;
import workshop.java.fundamentals.basics.bizz.Service;

public class DependenciesTest {

    @Mock
    public Database mockedDatabase;
    @Mock
    public RemoteService mockedService;
    @Captor
    public ArgumentCaptor<String> captor;
    @InjectMocks
    public Service serviceUnderTests;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoBusinessStuffFromService() throws Exception {
        Mockito.when(mockedDatabase.readData()).thenReturn("fake data");

        serviceUnderTests.doBusinessStuff();

        Mockito.verify(mockedService).doYourJob(captor.capture());

        Assertions.assertThat(captor.getValue()).isEqualTo("fake data to send");
    }
}

