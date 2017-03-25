package vue.composant.DatePickerFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {
	
	private String dateFormat = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

	@Override
	public Object stringToValue(String date) throws ParseException {
		return dateFormatter.parseObject(date);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar)value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}
	
}
