For Employee chargeability:-

Report will be generated based on below Query:-

SELECT td.emp_id, td.charge_code_id, ccm.charge_code_description, ccm.charge_code_owner, SUM(day_one+day_two+day_three+day_four+day_five+day_six+day_seven+day_eight+day_nine+day_ten+day_ten+day_eleven+day_twelve+
day_thirteen+day_fourteen+day_fifteen+day_sixteen) AS Total_Hours 
FROM timesheet_details td, charge_code_master ccm 
WHERE ccm.charge_code_id = td.charge_code_id AND 
td.emp_id='E002576' AND time_period_id=21 GROUP BY charge_code_id

------------------------------------------------------------------

SELECT td.emp_id, um.user_name, td.charge_code_id, ccm.charge_code_description, ccm.charge_code_owner, SUM(day_one+day_two+day_three+day_four+day_five+day_six+day_seven+day_eight+day_nine+day_ten+day_ten+day_eleven+day_twelve+
day_thirteen+day_fourteen+day_fifteen+day_sixteen) AS Total_Hours 
FROM timesheet_details td, charge_code_master ccm, user_master um 
WHERE ccm.charge_code_id = td.charge_code_id AND 
um.user_id = td.emp_id AND
td.emp_id='E001113' AND time_period_id=21 GROUP BY charge_code_id

For Supervisor reportees chargeability

SELECT DISTINCT td.emp_id, td.charge_code_id, ccm.charge_code_description, er.sup_emp_number AS supervisor_emp_id, 
td.day_one, td.day_two, td.day_three, td.day_four, td.day_five,
td.day_six, td.day_seven, td.day_eight, td.day_nine, td.day_ten, td.day_eleven, td.day_twelve,
td.day_thirteen, td.day_fourteen, td.day_fifteen, td.day_sixteen, td.time_period_id, td.status,
SUM(td.day_one+td.day_two+td.day_three+td.day_four+td.day_five+td.day_six+td.day_seven+td.day_eight+td.day_nine+td.day_ten
+td.day_eleven+td.day_twelve+
td.day_thirteen+td.day_fourteen+td.day_fifteen+td.day_sixteen) AS Total_Hours 
FROM user_master um, emp_reportto er, timesheet_details td, charge_code_master ccm
WHERE ccm.charge_code_id = td.charge_code_id AND
er.emp_number = td.emp_id AND 
td.status!='New' AND
FIND_IN_SET(um.user_id, er.sup_emp_number) AND 
FIND_IN_SET('E001286', er.sup_emp_number) AND 
time_period_id=21 GROUP BY td.emp_id, td.charge_code_id;

With few columns
SELECT DISTINCT td.emp_id, td.charge_code_id, ccm.charge_code_description, ccm.charge_code_owner, er.sup_emp_number AS supervisor_emp_id,
SUM(td.day_one+td.day_two+td.day_three+td.day_four+td.day_five+td.day_six+td.day_seven+td.day_eight+td.day_nine+td.day_ten
+td.day_eleven+td.day_twelve+
td.day_thirteen+td.day_fourteen+td.day_fifteen+td.day_sixteen) AS Total_Hours 
FROM user_master um, emp_reportto er, timesheet_details td, charge_code_master ccm
WHERE ccm.charge_code_id = td.charge_code_id AND
er.emp_number = td.emp_id AND 
td.status!='New' AND
FIND_IN_SET(um.user_id, er.sup_emp_number) AND 
FIND_IN_SET('E001286', er.sup_emp_number) AND 
time_period_id=21 GROUP BY td.emp_id, td.charge_code_id;


With User Names - Using Self Join
-----------------------------------
SELECT um1.user_name, td.emp_id, td.charge_code_id, ccm.charge_code_description, ccm.charge_code_owner, 
SUM(td.day_one+td.day_two+td.day_three+td.day_four+td.day_five+td.day_six+td.day_seven+td.day_eight+td.day_nine+td.day_ten
+td.day_eleven+td.day_twelve+
td.day_thirteen+td.day_fourteen+td.day_fifteen+td.day_sixteen) AS Total_Hours
FROM user_master um, user_master um1, emp_reportto er, timesheet_details td, charge_code_master ccm
WHERE er.emp_number = td.emp_id AND 
um1.user_id = td.emp_id AND
ccm.charge_code_id = td.charge_code_id AND
td.status != 'New' AND 
FIND_IN_SET(um.user_id, er.sup_emp_number) AND
FIND_IN_SET('E001286', er.sup_emp_number) AND 
time_period_id = 21
GROUP BY td.emp_id, td.charge_code_id;


With Designation ids:-

SELECT td.emp_id, um1.user_name, dm.desg_name, td.charge_code_id, ccm.charge_code_description, ccm.charge_code_owner, 
SUM(td.day_one+td.day_two+td.day_three+td.day_four+td.day_five+td.day_six+td.day_seven+td.day_eight+td.day_nine+td.day_ten
+td.day_eleven+td.day_twelve+
td.day_thirteen+td.day_fourteen+td.day_fifteen+td.day_sixteen) AS Total_Hours
FROM user_master um, user_master um1, emp_reportto er, timesheet_details td, charge_code_master ccm, designation_master dm
WHERE er.emp_number = td.emp_id AND 
um1.user_id = td.emp_id AND
um1.desgn_id = dm.desg_id AND
ccm.charge_code_id = td.charge_code_id AND
td.status != 'New' AND 
FIND_IN_SET(um.user_id, er.sup_emp_number) AND
FIND_IN_SET('E001322', er.sup_emp_number) AND 
time_period_id = 21
GROUP BY td.emp_id, td.charge_code_id;
