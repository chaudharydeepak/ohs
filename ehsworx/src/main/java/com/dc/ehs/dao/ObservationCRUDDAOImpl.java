package com.dc.ehs.dao;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.dc.ehs.common.EmailService;
import com.dc.ehs.entity.Actions;
import com.dc.ehs.entity.Attachment;
import com.dc.ehs.entity.Observation;
import com.dc.ehs.mapper.ActionMapper;
import com.dc.ehs.mapper.AttachmentMapper;
import com.dc.ehs.mapper.ObservationMapper;

/**
 * Implementation of Interface ObservationCRUDDAO interface.
 * 
 * @author deepakchaudhary
 *
 */
public class ObservationCRUDDAOImpl implements ObservationCRUDDAO
{
	private static final Logger LOGGER = Logger.getLogger(ObservationCRUDDAOImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	@Autowired
	EmailService emailService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#saveObservation(com.dc.ehs.entity.
	 * Observation)
	 */
	public int saveObservation(Observation observation) throws MessagingException, ParseException
	{
		int obs_id = 0;
		int actionNumber = 0;
		int attachNumber = 0;
		String _SQL = "";
		Map<String, Object> namedParameters;
		if (observation.getOperationType().equalsIgnoreCase("new"))
		{
			LOGGER.info("-----" + observation.getInitiatedBy());
			String fetchObsNm = "select t from ( values next value for app.observation_number_id ) s( t) ";
			obs_id = jdbcTemplate.queryForObject(fetchObsNm, Integer.class);
			_SQL = "insert into app.ObservationMaster ( obs_id, \n" + "status,\n" + "active,\n" + "reference,  \n"
					+ "location, \n" + "department, \n" + "observrType, \n" + "behalfOf,\n" + "contact_info, \n"
					+ "shoc, \n" + "classification, \n" + "obsTxt,\n" + "respMgr, \n" + "initiatedBy, \n"
					+ "creatd_dt, \n" + "creatd_by, obs_date, project, area) values (" + obs_id + ",\n" + "'Assigned'"
					+ ",\n" + "'true'" + ",\n" + "'" + observation.getObsRef() + "',\n" + "'"
					+ observation.getLocations() + "',\n" + "'" + observation.getDepartments() + "',\n" + "'"
					+ observation.getWhobsvd() + "',\n" + "'" + observation.getObsBehf() + "',\n" + "'"
					+ observation.getObsContctInfo() + "',\n" + "'" + observation.getShoc() + "',\n" + "'"
					+ observation.getClassification() + "',\n" + "'" + observation.getObsTxt() + "',\n" + "'"
					+ observation.getResponsibleManager() + "',\n" + "'" + observation.getInitiatedBy()
					+ "', CURRENT_DATE ,\n" + "'" + observation.getInitiatedBy() + "','" + observation.getDate() + "','"
					+ observation.getProject() + "','" + observation.getAreas() + "')";

			jdbcTemplate.update(_SQL);

			if (null != observation.getPropsdAction() && observation.getPropsdAction().trim().length() > 0)
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
						+ "," + "'" + observation.getPropsdAction().trim() + "')";
				jdbcTemplate.update(insertSQLAction);
			}

			if (null != observation.getPropsdAction_2() && observation.getPropsdAction_2().trim().length() > 0)
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
						+ "," + "'" + observation.getPropsdAction_2().trim() + "')";
				jdbcTemplate.update(insertSQLAction);
			}

			if (null != observation.getPropsdAction_3() && observation.getPropsdAction_3().trim().length() > 0)
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
						+ "," + "'" + observation.getPropsdAction_3().trim() + "')";
				jdbcTemplate.update(insertSQLAction);
			}

			if (null != observation.getPropsdAction_4() && observation.getPropsdAction_4().trim().length() > 0)
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
						+ "," + "'" + observation.getPropsdAction_4().trim() + "')";
				jdbcTemplate.update(insertSQLAction);
			}

			if (null != observation.getPropsdAction_5() && observation.getPropsdAction_5().trim().length() > 0)
			{
				actionNumber = actionNumber + 1;
				String insertSQLAction = "insert into APP.ObservationActions values(" + obs_id + "," + actionNumber
						+ "," + "'" + observation.getPropsdAction_5().trim() + "')";
				jdbcTemplate.update(insertSQLAction);
			}

			MultipartFile multipartFile = observation.getFile();

			LOGGER.info("--" + multipartFile);
			LOGGER.info("---" + multipartFile.getOriginalFilename());
			if (null != multipartFile && null != multipartFile.getOriginalFilename()
					&& !multipartFile.getOriginalFilename().trim().isEmpty())
			{
				attachNumber = attachNumber + 1;
				String insertSQLAttachment = "insert into APP.ObservationAttachmnts values(" + obs_id + ","
						+ attachNumber + "," + "'File'" + ",'" + observation.getFile().getOriginalFilename() + "',"
						+ "'/home/ec2-user/tools/docs'" + ")";
				jdbcTemplate.update(insertSQLAttachment);
			}

			/* Lets send email */

			String respManagerAry[] = observation.getResponsibleManager().split("\\(");
			String respManagerEmail = (respManagerAry[1]).replace(")", "");
			LOGGER.info("updated notification for " + respManagerEmail);
			String fetchInitiatorName = "select FIRST_NAME || ' ' || LAST_NAME from app.EHS_SECURITY_USERPROFILE where username=:username";
			String respManagerName = respManagerAry[0];
			namedParameters = new HashMap<String, Object>();
			namedParameters.put("username", observation.getInitiatedBy());
			String initiatorName = namedParameterJdbcTemplate.queryForObject(fetchInitiatorName, namedParameters,
					String.class);

			emailService.sendMail("chaudharydeepak08@gmail.com", respManagerEmail,
					"Observation assigned for your action/" + observation.getClassification() + "/" + obs_id,
					observation, observation.getFile().getOriginalFilename(), obs_id, initiatorName, respManagerName);

		} else
		{
			_SQL = "update app.ObservationMaster set reference=:ref, location=:loc, department=:dept, observrType=:obsType, behalfOf=:behalfOf, contact_info=:cntct, shoc=:sho, classification=:class"
					+ ", obsTxt=:obsText, respMgr=:resp, initiatedBy=:initby, modfd_dt=CURRENT_DATE, modfd_by=:mdfdby, project=:project, obs_date=:obsDate, area=:area where obs_id=:obsId and upper(status) not like '%COMP%'";

			namedParameters = new HashMap<String, Object>();

			namedParameters.put("ref", observation.getObsRef());
			namedParameters.put("loc", observation.getLocations());
			namedParameters.put("dept", observation.getDepartments());
			namedParameters.put("obsType", observation.getWhobsvd());
			namedParameters.put("behalfOf", observation.getObsBehf());
			namedParameters.put("cntct", observation.getObsContctInfo());
			namedParameters.put("sho", observation.getShoc());
			namedParameters.put("class", observation.getClassification());
			namedParameters.put("obsText", observation.getObsTxt());
			namedParameters.put("resp", observation.getResponsibleManager());
			namedParameters.put("initby", observation.getInitiatedBy());
			namedParameters.put("mdfdby", observation.getInitiatedBy());
			namedParameters.put("obsId", Integer.valueOf(observation.getObsID()));
			namedParameters.put("project", observation.getProject());
			namedParameters.put("obsDate", observation.getDate());
			namedParameters.put("area", observation.getAreas());
			namedParameterJdbcTemplate.update(_SQL, namedParameters);

			obs_id = Integer.valueOf(observation.getObsID());

			String _actionsSQL = "update app.ObservationActions set action_txt=:actionTXT where obs_id=:obsId and action_id=:actionID";

			List<Actions> _actionList = observation.getActionsList();

			_actionList.forEach(actionObj ->
			{
				LOGGER.warn(
						"check updated action item -- " + actionObj.getActionTxt() + " : " + actionObj.getActionId());
				namedParameters.put("actionTXT", actionObj.getActionTxt());
				namedParameters.put("actionID", actionObj.getActionId());
				namedParameters.put("obsId", Integer.valueOf(observation.getObsID()));
				namedParameterJdbcTemplate.update(_actionsSQL, namedParameters);
			});

			MultipartFile multipartFile = observation.getFile();

			LOGGER.info("--" + multipartFile);
			LOGGER.info("---" + multipartFile.getOriginalFilename());
			if (null != multipartFile && null != multipartFile.getOriginalFilename()
					&& !multipartFile.getOriginalFilename().trim().isEmpty())
			{
				String fetchCurrentAttchID = "select case when max(attch_id) is null then 1 else max(attch_id) end from APP.ObservationAttachmnts where obs_id=:obsid";
				namedParameters.put("obsid", Integer.valueOf(observation.getObsID()));
				int currentAttchID = namedParameterJdbcTemplate.queryForObject(fetchCurrentAttchID, namedParameters,
						Integer.class);
				currentAttchID = currentAttchID + 1;
				LOGGER.info("currentAttchID " + currentAttchID);

				String insertSQLAttachment = "insert into APP.ObservationAttachmnts values(" + obs_id + ","
						+ currentAttchID + "," + "'File'" + ",'" + observation.getFile().getOriginalFilename() + "',"
						+ "'/home/ec2-user/tools/docs'" + ")";

				jdbcTemplate.update(insertSQLAttachment);
			}
		}
		return obs_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#loadObservation(int)
	 */
	public Observation loadObservation(int observationId, String loggedInUser)
	{
		Observation obsMaster = null;
		String fetchObservation = "select o.*, u.first_name, u.last_name from app.ObservationMaster o, app.EHS_SECURITY_USERPROFILE u where o.initiatedby = u.username and obs_id=:obsid and o.active=true";
		if (!hasRole("ROLE_ADMIN"))
		{
			fetchObservation = fetchObservation + " and o.respmgr like '%" + loggedInUser + "%'";
		}

		Map<String, Object> namedParameters = new HashMap<String, Object>();

		namedParameters.put("obsid", observationId);

		List<Observation> obsFrmDB = namedParameterJdbcTemplate.query(fetchObservation, namedParameters,
				new ObservationMapper());

		if (null != obsFrmDB && !obsFrmDB.isEmpty())
		{
			obsMaster = obsFrmDB.get(0);

			if (null != obsMaster)
			{
				/* fetch actions */
				String fetchActions = "Select * from app.ObservationActions where obs_id=:obsid";

				List<Actions> actionList = namedParameterJdbcTemplate.query(fetchActions, namedParameters,
						new ActionMapper());

				if (null != actionList && !actionList.isEmpty())
					obsMaster.setActionsList(actionList);

				/* fetch attacments */
				String fetchAttach = "Select * from app.ObservationAttachmnts where obs_id=:obsid";
				List<Attachment> attachList = namedParameterJdbcTemplate.query(fetchAttach, namedParameters,
						new AttachmentMapper());

				if (null != attachList && !attachList.isEmpty())
				{
					LOGGER.info("##################setting attachment --> " + attachList.size());
					obsMaster.setAttachList(attachList);
				}
			}
		}
		return obsMaster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#loadAllObservations()
	 */
	public List<Observation> loadAllObservations(String username)
	{
		String fetchAllObservations = "select o.*, u.first_name, u.last_name from app.ObservationMaster o, app.EHS_SECURITY_USERPROFILE u where o.initiatedby = u.username and o.active = true";
		List<Observation> obsList = null;
		if (!hasRole("ROLE_ADMIN"))
		{
			LOGGER.info("username is not admin -- fetching assigned obs :");
			fetchAllObservations = fetchAllObservations + " and respMgr like '%" + username + "%'";
		}
		obsList = namedParameterJdbcTemplate.query(fetchAllObservations, new ObservationMapper());

		Map<String, Object> namedParameters = new HashMap<String, Object>();

		obsList.forEach(item ->
		{

			if (null != item)
			{
				/* fetch actions */
				String fetchActions = "Select * from app.ObservationActions where obs_id=:obsid";
				namedParameters.put("obsid", Integer.valueOf(item.getObsID()));
				List<Actions> actionList = namedParameterJdbcTemplate.query(fetchActions, namedParameters,
						new ActionMapper());

				if (null != actionList && !actionList.isEmpty())
					item.setActionsList(actionList);

				/*
				 * fetch attacments String fetchAttach =
				 * "Select * from app.ObservationAttachmnts where obs_id=:obsid";
				 * List<Attachment> attachList = namedParameterJdbcTemplate.query(fetchAttach,
				 * namedParameters, new AttachmentMapper());
				 * 
				 * if (null != attachList && !attachList.isEmpty()) {
				 * LOGGER.info("##################setting attachment --> " + attachList.size());
				 * item.setAttachList(attachList); }
				 */
			}
		});
		return obsList;
	}

	/*
	 * Utility method - move to util class.
	 */
	@SuppressWarnings("unchecked")
	private static boolean hasRole(String role)
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities)
		{
			hasRole = authority.getAuthority().equals(role);
			if (hasRole)
			{
				break;
			}
		}
		return hasRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#setStatusOnObservation(int,
	 * java.lang.String, java.lang.String, java.lang.String,
	 * org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String setStatusOnObservation(int observationId, String status, String user, String actionComments,
			MultipartFile multipartFile)
	{
		String _SQL = "update app.ObservationMaster set modfd_dt=CURRENT_DATE, modfd_by=:mdfdby, status=:status, actiontxt=:actionTxt where obs_id=:obsId";

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("mdfdby", user);
		namedParameters.put("status", status);
		namedParameters.put("obsId", observationId);
		namedParameters.put("actionTxt", actionComments);
		namedParameterJdbcTemplate.update(_SQL, namedParameters);

		MultipartFile _multipartFile = multipartFile;

		LOGGER.info("--" + _multipartFile);

		if (null != _multipartFile && null != _multipartFile.getOriginalFilename()
				&& !_multipartFile.getOriginalFilename().trim().isEmpty())
		{
			String fetchCurrentAttchID = "select case when max(attch_id) is null then 1 else max(attch_id) end from APP.ObservationAttachmnts where obs_id=:obsid";
			namedParameters.put("obsid", observationId);
			int currentAttchID = namedParameterJdbcTemplate.queryForObject(fetchCurrentAttchID, namedParameters,
					Integer.class);
			currentAttchID = currentAttchID + 1;
			LOGGER.info("currentAttchID " + currentAttchID);

			String insertSQLAttachment = "insert into APP.ObservationAttachmnts values(" + observationId + ","
					+ currentAttchID + "," + "'File'" + ",'" + _multipartFile.getOriginalFilename() + "',"
					+ "'/Users/deepakchaudhary/dc_consulting/EHS/uploaded_docs/'" + ")";

			jdbcTemplate.update(insertSQLAttachment);
		}

		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.ehs.dao.ObservationCRUDDAO#deleteObservation(int,
	 * java.lang.String)
	 */
	@Override
	public String deleteObservation(int obsId, String mdfdby)
	{
		String _SQL = "update app.ObservationMaster set active= false, modfd_dt=CURRENT_DATE, modfd_by=:mdfdby where obs_id=:obsId";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("mdfdby", mdfdby);
		namedParameters.put("obsId", obsId);
		namedParameterJdbcTemplate.update(_SQL, namedParameters);
		return "success";
	}

	/**
	 * 
	 */
	@Override
	public String fetchRespManagerForObs(int obsID)
	{
		String _SQL = "select respMgr from app.ObservationMaster where obs_id=:obsID";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("obsID", obsID);
		return namedParameterJdbcTemplate.queryForObject(_SQL, namedParameters, String.class);
	}

	/**
	 * 
	 */
	@Override
	public String fetchStatusForObs(int obsID)
	{
		String _SQL = "select status from app.ObservationMaster where obs_id=:obsID";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("obsID", obsID);
		return namedParameterJdbcTemplate.queryForObject(_SQL, namedParameters, String.class);
	}
}
