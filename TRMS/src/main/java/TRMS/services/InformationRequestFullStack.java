package TRMS.services;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.InformationRequestDao;
import TRMS.dao.InformationRequestDaoPostgres;
import TRMS.models.InformationRequest;

public class InformationRequestFullStack implements InformationRequestService {
	
	private InformationRequestDao informationDao = new InformationRequestDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public void createInfoRequest(InformationRequest infoRequest) {

		log.info("Information service creating request");
		
		informationDao.createInfoRequest(infoRequest);
	}

	@Override
	public InformationRequest readInfoRequest(int infoId) {

		log.info("Information service reading request");
		
		return informationDao.readInfoRequest(infoId);
	}

	@Override
	public List<InformationRequest> readAllInfoRequest() {
		
		log.info("Information service reading all requests");
		
		return informationDao.readAllInfoRequest();
	}

	@Override
	public InformationRequest updateInfoRequest(int infoId, InformationRequest infoRequest) {

		log.info("Information service updating request");
		
		return informationDao.updateInfoRequest(infoId, infoRequest);
	}

	@Override
	public void deleteRequest(InformationRequest infoRequest) {

		log.info("Information service deleting request");
		
		informationDao.deleteRequest(infoRequest);

	}

}
