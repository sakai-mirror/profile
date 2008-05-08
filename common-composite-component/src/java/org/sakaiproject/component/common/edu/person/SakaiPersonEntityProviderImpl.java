package org.sakaiproject.component.common.edu.person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.api.common.edu.person.SakaiPerson;
import org.sakaiproject.api.common.edu.person.SakaiPersonEntityProvider;
import org.sakaiproject.api.common.edu.person.SakaiPersonManager;
import org.sakaiproject.entitybroker.entityprovider.CoreEntityProvider;
import org.sakaiproject.entitybroker.entityprovider.capabilities.AutoRegisterEntityProvider;


public class SakaiPersonEntityProviderImpl implements
		SakaiPersonEntityProvider, CoreEntityProvider,
		AutoRegisterEntityProvider {

	private static Log log = LogFactory.getLog(SakaiPersonEntityProviderImpl.class);
	
	private SakaiPersonManager sakaiPersonManager;
	public void setSakaiPersonManager(SakaiPersonManager spm) {
		sakaiPersonManager = spm;
	}
	
	public String getEntityPrefix() {
		return ENTITY_PREFIX;
	}

	public boolean entityExists(String id) {
		Long sakaiPersonId;
		
		sakaiPersonId = new Long(id);
		try{
			SakaiPerson sp = sakaiPersonManager.getSakaiPersonById(sakaiPersonId);
			if (sp != null)
				return true;

		} catch (NumberFormatException e) {
			// invalid number so roll through to the false
		}

		log.warn("SakaiPerson: " + id +" does not exist");
		return false;
	}

}
