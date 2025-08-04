show databases;
use moviedb;
 insert into moviedetails(release_date,Actors,Movietype,Director,Rating,Language)
 values
 ('2026-01-02', 'Vijay_&_Tamanah_&_Santhanam', 'Political story', 'Karthik Subraj', 4, 'Tamil_Telugu'),
 ('2026-02-14', 'Ajith_&_Shalini', 'Romantic story', 'Manirathnam', 3, 'Tamil_Malayalam'),
 ('2026-09-19', 'Surya_&_Anushka_&_Soori', 'Motivation_&_MixedFight_&_Romantic','Venkat_Prabhu', 5, 'Tamil_Telugu');
 
 describe moviedetails;
 select * from moviedetails;
 
 show tables;
 describe productiondetails;
 insert into productiondetails 
 values('Red_giant_movies', 'Udhayanidhi_stalin'), ('Green_studio', 'Nyanavel_raja'), ('Sun Pictures' , 'Kalanithi_maran');
 
 select * from productiondetails;
 
 create table Streaming(Platfrom varchar(50) unique, Subscription_cost varchar(60), Streamingdate date); 
 create table old_movies(Movie_name varchar(100) unique, Websites varchar(80), released_year date);

 alter table Streaming drop Streamingdate; 
 describe Streaming;
 insert into Streaming 
 values('Amazonprime', '2500/year'),
 ('Disney_Hotstar', '2000/year'),
 ('Zee5', '1500/year');
 
 describe old_movies;
 insert into old_movies
 values('Mudhal_Nee_Mudivum_Nee', 'moviesda.com', '2022-03-18'),
 ('Kadhal_Dessam', 'kuttymovies.com', '1996-05-20'),
 ('Kathi', 'tamilplay.com', '2015-08-19');
 
 show tables;
 create table permium_user(username varchar(50) unique, password varchar(70) unique, total_spent_cost int);
 create table normal_user(username varchar(50) unique, password varchar(80) unique, email_id varchar(90));
 alter table permium_user add email_id varchar(100);
 alter table permium_user rename to premium_user;
 
 insert into premium_user 
 values('Villan_007', 'come_and_fun', 3000),
 ('pookie_no1', 'strong@pass', 4000);
 
 
 
 
 
 