# TestProject
For Q1 -->  

Steps are : 
  1) Import Maven project in to Eclipse 
  2) Expand com.sample.MavenProject
  3) Navigate Run --> Run Configuration
  4) Click on (x)=Arguments, need to provide data file name . In our case it is q1.test_data
  5) Click on Apply and Run
  6) Console output :
  
  ===========
GROUP : STARTS
Key = smh_seg_version, Value = "00"
Key = smh_seg_id, Value = "000000"
Key = smh_corr_id, Value = "cc04cef7-8cac-48ae-9487-7730d6ff4797"
Key = smh_activity_detail, Value = "CAT"
Key = smh_tran_type, Value = "ABC"
Key = smh_tran_num, Value = "89456234131861"								
Key = smh_cust_type, Value = "SA"								
Key = sub_seg_id, Value = "099990"
Key = sub_seg_version, Value = "99"
Key = sub_api_processing, Value = "9"
Key = sub_rur_persistence, Value = "90"
Key = rur_seg_version, Value = "111"
Key = rur_seg_id, Value = "011111"
GROUP : STARTS
Key = smh_seg_version, Value = "22"
Key = smh_seg_id, Value = "000020"
Key = smh_corr_id, Value = "0f02caca-d723-4c7c-842d-8b6468be65f8"
Key = smh_activity_detail, Value = "RED"
Key = smh_tran_type, Value = "TST"								
Key = smh_tran_num, Value = "87456234132583"								
Key = rem_seg_version, Value = "85"
Key = rem_seg_id, Value = "043990"
Key = rem_api_processing, Value = "978"
Key = rem_psn_system, Value = "SAT"
Key = rur_seg_version, Value = "658"
Key = rur_seg_id, Value = "658974"

  
 Note: 
 1) Output files are key-value.xlsx and key-value(0).csv and key-value(1).csv
 
For Q2 : --

Steps :--
1) Navigate src/test/java --> com.homeloan.tests 
2) Run TestHomeLoan.java as JUnit Test

Note: Snippet of console output 

  Before driver.get...
current url: 	https://www.commbank.com.au/
>>> Method name : test_HomeLoanRepay
>>> Class name : com.homeloan.tests.TestHomeLoan

>>> Log File with Path :  C:\Users\User\eclipse-workspace\TestProject\logs\com.homeloan.tests.TestHomeLoan.test_HomeLoanRepay_result.log
Total Repayemnt: $279,591
Total Interest: $79,591
----------------------------------------
Repayment Type : Interest only 1 year
Total Repayemnt: $283,345
Total Interest: $83,345
Total Repayemnt: $306,421
Total Interest: $106,421
Total Repayemnt: $340,977
Total Interest: $140,977
Total Repayemnt: $339,677

2) The log file TestHomeLoan.test_HomeLoanRepay_result.log is generated under logs folder.
