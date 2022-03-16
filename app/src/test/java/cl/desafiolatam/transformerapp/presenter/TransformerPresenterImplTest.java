package cl.desafiolatam.transformerapp.presenter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import cl.desafiolatam.transformerapp.TransformerPresenterImpl;
import cl.desafiolatam.transformerapp.TransformerView;

@RunWith(MockitoJUnitRunner.class)
public class TransformerPresenterImplTest {



    @Mock
    private TransformerView view;
    private TransformerPresenterImpl presenter;

    private final Calendar calendarDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    @Before
    public void setUp() throws Exception {
   view = mock(TransformerView.class);
   presenter = new TransformerPresenterImpl();
   presenter.setView(view);

        calendarDate.set(1996,5,26);
        Long date = calendarDate.getTime().getTime();
        presenter.setDate(date);
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void setDate() {
       Long esperado = calendarDate.getTime().getTime();
        assertEquals(esperado,presenter.getTimeStamp());
    }

    @Test
    public void getStringDate() {

        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String  dateString = format.format(calendarDate.getTime());

        assertEquals(dateString, presenter.getStringDate());
    }

    @Test
    public void getDaysOnlyCorrecto() {
        String dia1 = "Día del mes: 26";
        assertEquals(dia1, presenter.getDaysOnly());
    }
    @Test
    public void getDaysOnlyIncorrecto() {
        String dia2 = "Día del mes: 12";
        assertNotEquals(dia2, presenter.getDaysOnly());
    }

    @Test
    public void getWeeksOnly() {
        String semana1 = "Semana del año: 26";
        assertEquals(semana1,presenter.getWeeksOnly());
    }
    @Test
    public void getWeeksOnlyIncorrectos() {
        String semana1 = "Semana del año: 29";
        assertNotEquals(semana1,presenter.getWeeksOnly());
    }
    @Test
    public void getWeeksOnlyNull() {
        String semana1 = "Semana del año: ";
        assertNotNull(semana1,presenter.getWeeksOnly());
    }

    @Test
    public void getTimeStamp() {
        Long esperado = calendarDate.getTimeInMillis();
        assertEquals(esperado,presenter.getTimeStamp());
    }
    @Test
    public void getTimeStampIncorrecto() {
        Long esperado = calendarDate.getTimeInMillis() + 31;
        assertNotEquals(esperado,presenter.getTimeStamp());
    }

    @Test
    public void getDateFormatOne() {
        String dateFormat = "26/06/1996";
        assertEquals(dateFormat,presenter.getDateFormatOne());
    }
    @Test
    public void getDateFormatOneNull() {
        String dateFormat = "";
        assertNotNull(dateFormat,presenter.getDateFormatOne());
    }

    @Test
    public void getDateFormatTwo() {

        String dateFormat = "26 - 06 - 1996";
        assertEquals(dateFormat,presenter.getDateFormatTwo());
    }
    @Test
    public void getDateFormatTwoIncorrect() {

        String dateFormat = "26 - 06 - 1995";
        assertNotEquals(dateFormat,presenter.getDateFormatTwo());
    }

    @Test
    public void getDateFormatThree() {

        String dateFormat = "mié., jun. 26, 1996";
        assertEquals(dateFormat,presenter.getDateFormatThree());
    }
    @Test
    public void getDateFormatThreenNull() {

        String dateFormat = "";
        assertNotNull(dateFormat,presenter.getDateFormatThree());
    }

    @Test
    public void getDateFormatFour() {

        String dateFormat = "miércoles 26";
        assertEquals(dateFormat,presenter.getDateFormatFour());
    }
    @Test
    public void getDateFormatFourINCORRECTO() {


        String dateFormat = "miércoles 20";
        assertEquals(dateFormat,presenter.getDateFormatFour());
    }
}