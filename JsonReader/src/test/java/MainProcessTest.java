import com.luxoft.omalovanyi.main.MainProcess;
import com.luxoft.omalovanyi.reader.FolderReader;
import com.luxoft.omalovanyi.reader.JsonReader;
import com.luxoft.omalovanyi.reader.JsonReaderException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * Created by maoleks on 6/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testApplicationContext.xml")
public class MainProcessTest {

    @Mock
    JsonReader jsonReader;

    @InjectMocks
    @Autowired
    private MainProcess mainReader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
      public void testStartProcess() throws Exception{
        mainReader.startProcess();
        verify(jsonReader, atLeast(1)).read();
        verify(jsonReader, atLeast(1)).sort();
        verify(jsonReader, atLeast(1)).save();
    }

}
