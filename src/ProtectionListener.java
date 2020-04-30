import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ProtectionListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	//Déconnecte l'utlisateur si son IP ou son navigateur est modifié
    public void attributeReplaced(HttpSessionBindingEvent sessionEvent)
    {
		HttpSession s = sessionEvent.getSession();
		String name = sessionEvent.getName();
		if (name.equals("ip"))
		{
			s.invalidate();
		}
		if (name.equals("user-agent"))
		{
			s.invalidate();
		}
    }
}
