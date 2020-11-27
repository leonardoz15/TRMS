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
		// TODO Auto-generated method stub

	}

	@Override
	public ReimbursementRequest readRequest(int requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementRequest> readAllGuests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementRequest updateRequest(int requestId, ReimbursementRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRequest(ReimbursementRequest request) {
		// TODO Auto-generated method stub

	}

}
