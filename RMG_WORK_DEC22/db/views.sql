CREATE OR REPLACE VIEW login_details_master_vw

AS

SELECT um.user_id AS employee_id, 
um.user_name AS employee_name, 
rm.role_id AS emp_role_id, 
rm.role_name AS emp_role_name,
um.du_id AS delivery_unit 
FROM user_master um, role_master rm, user_role_map urm 
WHERE urm.user_id = um.user_id AND urm.role_id = rm.role_id;


CREATE OR REPLACE VIEW emp_timesheet_details_vw
AS
SELECT td.emp_id AS employee_id, um.user_name AS employee_name, td.loc_id AS loc_id, lm.location_name AS location_name, td.charge_code_id AS assignment_id, ccm.charge_code AS project_code, 
       ccm.charge_code_description AS project_desc, tpm.time_period_id AS period_id, tpm.time_period_name AS period_name,
       td.status AS tstatus, td.comments, td.day_one AS day_one, td.day_two AS day_two, td.day_three AS day_three, td.day_four AS day_four, 
       td.day_five AS day_five, td.day_six AS day_six, td.day_seven AS day_seven, td.day_eight AS day_eight, 
       td.day_nine AS day_nine, td.day_ten AS day_ten, td.day_eleven AS day_eleven, 
       td.day_twelve AS day_twelve, td.day_thirteen AS day_thirteen, td.day_fourteen AS day_fourteen, 
       td.day_fifteen AS day_fifteen, td.day_sixteen AS  day_sixteen
FROM timesheet_details td, charge_code_master ccm, time_period_master tpm, user_master um, location_master lm
 
WHERE td.charge_code_id = ccm.charge_code_id AND 
      td.emp_id = um.user_id AND
      td.loc_id = lm.loc_id AND
      td.time_period_id = tpm.time_period_id;
	  

CREATE OR REPLACE VIEW resource_list_vw
AS
SELECT DISTINCT er.emp_number AS employee_id, er.sup_emp_number AS supervisor_emp_id, er.appr_emp_number AS approver_emp_id, 
er.copyto_emp_number AS copyto_emp_number, td.time_period_id, td.status
FROM user_master um, emp_reportto er, timesheet_details td
WHERE er.emp_number = td.emp_id AND FIND_IN_SET(um.user_id, er.appr_emp_number);
	  
	   
CREATE OR REPLACE VIEW get_last_date_vw

AS

SELECT CURDATE() currDate, CASE WHEN CURDATE() <= DATE_FORMAT(NOW() ,'%Y-%m-15') THEN DATE_FORMAT(NOW() ,'%Y-%m-15')
ELSE LAST_DAY(CURDATE()) END AS last_date


CREATE OR REPLACE VIEW current_period_details_vw

AS

SELECT tpm.time_period_id, tpm.time_period_name, tpm.time_period_lastdate 
FROM get_last_date_vw ldv, time_period_master tpm 
WHERE ldv.last_date = tpm.time_period_lastdate;	   

CREATE OR REPLACE VIEW user_charge_code_map_vw
AS
SELECT  ccm.charge_code, ccm.charge_code_description,ucm.emp_charg_code_map_id, ucm.emp_id, 
       ucm.charge_code_id, ucm.time_period_id, ucm.charge_code_assign_by, 
       ucm.user_charge_code_assign_date, ccm.charge_code_owner 
FROM user_charge_code_map ucm, charge_code_master ccm WHERE ucm.charge_code_id = ccm.charge_code_id  