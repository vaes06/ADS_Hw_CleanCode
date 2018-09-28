package pe.edu.unmsm.fisi.upg.ads.dirtycode.domain;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.SpeakerDoesntMeetRequirementsException;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.exceptions.NoSessionsApprovedException;
import pe.edu.unmsm.fisi.upg.ads.dirtycode.infrastructure.SqlServerRepository;

public class SpeakerTest {
	private SqlServerRepository repository = new SqlServerRepository();

	@Test(expected = IllegalArgumentException.class)
	public void registerSpeakerData(){
		Speaker speaker = getSpeaker();
		speaker.setFirstName("");
		speaker.setLastName("");
		speaker.setEmail("");

		speaker.register(repository);
	}

	@Test
	public void registerSpeakerWithRedFlags() throws Exception {
		Speaker speaker = getSpeakerWithRedFlags();
		speaker.setEmployer("Microsoft");
		speaker.setCertifications(Arrays.asList("cert1", "cert2", "cert3", "cert4"));

		Integer speakerId = speaker.register(new SqlServerRepository());

		assertNotNull(speakerId);
	}


	@Test(expected = NoSessionsApprovedException.class)
	public void register_SingleSessionThatsOnOldTech() throws Exception {
		Speaker speaker = getSpeaker();
		speaker.setSessions(Arrays.asList(new Session("Cobol for dummies", "Intro to Cobol")));

		speaker.register(repository);
	}

	@Test(expected = IllegalArgumentException.class)
	public void register_NoSessionsPassed() throws Exception {
		Speaker speaker = getSpeaker();
		speaker.setSessions(new ArrayList<Session>());
	
		speaker.register(repository);
	}

	@Test(expected = SpeakerDoesntMeetRequirementsException.class)
	public void register_DoesntAppearExceptionalAndUsingOldBrowser() throws Exception {
		Speaker speakerThatDoesntAppearExceptional = getSpeaker();
		speakerThatDoesntAppearExceptional.setHasBlog(false);
		speakerThatDoesntAppearExceptional.setBrowser(new WebBrowser("IE", 6));

		speakerThatDoesntAppearExceptional.register(repository);
	}

	@Test(expected = SpeakerDoesntMeetRequirementsException.class)
	public void register_DoesntAppearExceptionalAndHasAncientEmail() throws Exception {
		Speaker speakerThatDoesntAppearExceptional = getSpeaker();
		speakerThatDoesntAppearExceptional.setHasBlog(false);
		speakerThatDoesntAppearExceptional.setEmail("name@aol.com");

		speakerThatDoesntAppearExceptional.register(repository);
	}

	private Speaker getSpeaker() {
		Speaker speaker = new Speaker();

		speaker.setFirstName("First");
		speaker.setLastName("Last");
		speaker.setEmail("example@domain.com");
		speaker.setEmployer("Example Employer");
		speaker.setHasBlog(true);
		speaker.setBrowser(new WebBrowser("test", 1));
		speaker.setYearsOfExperience(1);
		speaker.setCertifications(new ArrayList<String>());
		speaker.setBlogURL("");
		speaker.setSessions(Arrays.asList(new Session("test title", "test description")));

		return speaker;
	}

	private Speaker getSpeakerWithRedFlags() {
		Speaker speaker = getSpeaker();
		speaker.setEmail("tom@aol.com");
		speaker.setBrowser(new WebBrowser("IE", 6));
		return speaker;
	}
}