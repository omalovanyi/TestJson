import com.luxoft.omalovanyi.dao.PlayerDAO;
import com.luxoft.omalovanyi.parser.JsonFileParser;
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

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * Created by maoleks on 6/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testApplicationContext.xml")
public class JsonReaderTest {

    @Mock
    JsonFileParser jsonFileParser;

    @Mock
    private FolderReader folderReader;

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    @Autowired
    private JsonReader jsonReader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRead() throws Exception{
        jsonReader.read();
        verify(folderReader, atLeast(1)).getListFiles();
    }

    @Test
    public void testSave() throws JsonReaderException{
        jsonReader.save();
        verify(playerDAO, atLeast(1)).clearListPlayers();
        verify(playerDAO, atLeast(1)).insertListPlayers(anyList());
    }
}
