CREATE VIEW OR REPLACE VIEW login_details_master

AS

SELECT um.user_id AS employee_id, 
um.user_name AS employee_name, 
rm.role_id AS emp_role_id, 
rm.role_name AS emp_role_name 
FROM user_master um, role_master rm, user_role_map urm 
WHERE urm.user_id = um.user_id AND urm.role_id = rm.role_id;