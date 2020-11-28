package TRMS.services;

import java.util.List;

import org.apache.log4j.Logger;

import TRMS.dao.ReimbursementRequestDao;
import TRMS.dao.ReimbursementRequestDaoPostgres;
import TRMS.models.ReimbursementRequest;

public class ReimbursementRequestFullStack implements ReimbursementRequestService {
	
	private ReimbursementRequestDao reimbursementDao = new ReimbursementRequestDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public void createRequest(ReimbursementRequest request) {
		
		log.info("Reimbursement service creating request");
		
		reimbursementDao.createRequest(request);

	}

	@Override
	public ReimbursementRequest readRequest(int requestId) {

		log.info("Reimbursement service reading request");
		
		return reimbursementDao.readRequest(requestId);
	}

	@Override
	public List<ReimbursementRequest> readAllRequests() {

		log.info("Reimbursement service reading all requests");
		
		return reimbursementDao.readAllRequests();
	}

	@Override
	public ReimbursementRequest updateRequest(int requestId, ReimbursementRequest request) {
		
		log.info("Reimbursement service updating request");
		
		return reimbursementDao.updateRequest(requestId, request);
	}

	@Override
	public void deleteRequest(ReimbursementRequest request) {

		log.info("Reimbursement request deleting request");
		
		reimbursementDao.deleteRequest(request);

	}

}
