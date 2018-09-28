package pe.edu.unmsm.fisi.upg.ads.dirtycode.domain;

import java.util.Arrays;
import java.util.List;

import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.SpeakerDoesntMeetRequirementsException;

public class Speaker {
	private String firstName;
	private String lastName;
	private String email;
	private int yearsOfExperience;
	private boolean hasBlog;
	private String blogURL;
	private WebBrowser browser;
	private List<String> certifications;
	private String employer;
	private int registrationFee;
	private List<Session> sessions;




	public Integer register(IRepository repository) throws Exception {
		Integer speakerId = null;
		boolean isQuialified = false;
		boolean isApproved = false;
		boolean hasGoodTools = false;

		if (this.firstName.isEmpty() throw new IllegalArgumentException("First Name is required");
		if (this.lastName.isEmpty()) throw new IllegalArgumentException("Last name is required.");
		if (this.email.isEmpty())	throw new IllegalArgumentException("Email is required.");

		List<String> companiesList = Arrays.asList("Pluralsight", "Microsoft", "Google", "Fog Creek Software", "37Signals", "Telerik");

		int certificacionsNeeded = 3;
		int minimunYearsOfExperience = 10;
		int minimunSessions = 1;

		isQuialified = ((this.yearsOfExperience > minimunYearsOfExperience || this.hasBlog || this.certifications.size() > certificacionsNeeded || companiesList.contains(this.employer)));
		hasGoodTools = checkIfGoodOnlineTools(WebBrowser browser, email);

		if (!isQuialified || !hasGoodTools) throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our abitrary and capricious standards.");
		if (this.sessions.size() >= minimunSessions)  throw new IllegalArgumentException("Can't register speaker with no sessions to present.");

		isApproved = approveTechSessions(List<Session> sessions);

		if (!isApproved) throw new NoSessionsApprovedException("No sessions approved.");

		this.registrationFee = repository.GetRegistrationFee(yearsOfExperience);

		try {
			speakerId = repository.saveSpeaker(this);
		} catch (Exception e) {
		}
		return speakerId;
	}

	public boolean checkIfGoodOnlineTools(WebBrowser browser, String email) {
		List<String> domains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "compuserve.com");
		String[] splittedMail = this.email.split("@");
		String emailDomain = splittedMail[splittedMail.length - 1];
		int minimumIEver= 9;
		if (!domains.contains(emailDomain) && (!(browser.getName() == this.WebBrowser.BrowserName.InternetExplorer && browser.getMajorVersion() < minimumIEver))){
			return true;
		}
	}

	public boolean approveTechSessions(List<Session> sessions){}
		String[] techSessions = new String[] { "Cobol", "Punch Cards", "Commodore", "VBScript" };
		for (Session session : sessions) {
			for (String tech : techSessions) {
				if (session.getTitle().contains(tech) || session.getDescription().contains(tech)) {
					session.setApproved(true);
					return true
				} else {
					session.setApproved(false);
					return false
				}
			}
		}
	}


	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExp() {
		return exp;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public boolean isHasBlog() {
		return hasBlog;
	}

	public void setHasBlog(boolean hasBlog) {
		this.hasBlog = hasBlog;
	}

	public String getBlogURL() {
		return blogURL;
	}

	public void setBlogURL(String blogURL) {
		this.blogURL = blogURL;
	}

	public WebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(WebBrowser browser) {
		this.browser = browser;
	}

	public List<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public int getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(int registrationFee) {
		this.registrationFee = registrationFee;
	}
}