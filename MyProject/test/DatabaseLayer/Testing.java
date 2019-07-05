package DatabaseLayer;
import org.junit.Test;

import junit.framework.TestCase;
public class Testing extends TestCase {

	@Test (expected=IllegalArgumentException.class)
	public void testConvertToText() {
		ConvertPDFToText text=new ConvertPDFToText();
		try {
			text.ConvertToText(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
}
