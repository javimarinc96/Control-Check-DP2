
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationsRepository;
import domain.Actor;
import domain.Administrator;
import domain.Configurations;
import domain.Message;

@Service
@Transactional
public class ConfigurationsService {

	// Manage Repository
	@Autowired
	private ConfigurationsRepository	configurationsRepository;

	// Supporting services
	@Autowired
	private ActorService				actorService;

	@Autowired
	private MessageService				messageService;


	// CRUD methods
	public Configurations getConfiguration() {
		final Configurations result = this.configurationsRepository.findAll().get(0);

		Assert.notNull(result);

		return result;
	}

	public Configurations save(final Configurations config) {
		Assert.notNull(config);

		Actor principal;

		// Principal must be a Administrator
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		this.checkConfiguration(config);

		if (this.getConfiguration().getSystemName() != config.getSystemName()) {
			final Message m = this.messageService.create();
			m.setBody("The application has been rebranded");
			m.setSubject("Application Rebranded");

			this.messageService.broadcast(m);
		}

		return this.configurationsRepository.save(config);
	}
	public void flush() {
		this.configurationsRepository.flush();
	}

	// Other business methods ---------------------------------------------------------

	public void checkConfiguration(final Configurations config) {
		boolean check = true;

		if (config.getBanner() == null || config.getSystemName() == null || config.getWelcomeMessageEn() == null || config.getWelcomeMessageEs() == null)
			check = false;

		Assert.isTrue(check);
	}
}
