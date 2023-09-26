-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 03, 2022 at 05:51 PM
-- Server version: 8.0.29-cll-lve
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amaldz_travel_mate`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int NOT NULL,
  `hotel_booking` text NOT NULL,
  `bus_booking` text NOT NULL,
  `user_id` text NOT NULL,
  `booking_id` text NOT NULL,
  `day` text NOT NULL,
  `from_date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `hotel_booking`, `bus_booking`, `user_id`, `booking_id`, `day`, `from_date`) VALUES
(1, '0', '1', '3', '2', '2', '29/6/2022'),
(2, '0', '1', '3', '2', '2', '29/6/2022'),
(3, '0', '1', '3', '1', '', 'Select date'),
(10, '0', '1', '1', '4', '6', '27/7/2022'),
(11, '0', '1', '1', '4', '6', '28/7/2022'),
(12, '0', '1', '1', '4', '9', '30/7/2022'),
(13, '0', '1', '1', '4', '9', '30/7/2022'),
(14, '0', '1', '7', '4', '34', '28/7/2022'),
(15, '0', '1', '7', '4', '56', '30/7/2022'),
(16, '0', '1', '7', '4', '3', '28/7/2022'),
(17, '0', '1', '7', '4', '6', '20/7/2022'),
(18, '0', '1', '7', '4', '3', '28/7/2022'),
(19, '0', '1', '7', '4', '5', '26/7/2022');

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`id`, `name`, `description`, `image`) VALUES
(1, 'Volvo', 'Volvo Buses is a subsidiary and a business area of the Swedish vehicle maker Volvo, which became an independent division in 1968. It is based in Gothenburg. It is one of the world\'s largest bus manufacturers, with a complete range of heavy buses for passenger transportation', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu1lBZinfzX3vI4dzF9shLn0yTTFne6ISjBA&usqp=CAU'),
(2, 'Party Bus', 'What do you call a tour bus?\r\nAlso known as “sleeper buses”, “entertainer buses”, or “nightliners”, tour coaches are built with artists, entertainers, and other high-value clients in mind.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmf-0L4xCfCGw11pT5kdSNTEDgg2JAXT9YYC6TDySV8ga1ns9_ZpOv6U2dVz9pdjlddm4&usqp=CAU'),
(3, 'Gray Volvo', 'tour bus service is an escorted tour or bus service that takes visitors sightseeing, with routes around tourist attractions.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5hn-zVXN3pQ99s1qgHedyBPbLrw6YL5BAQbevt2lNj4oC0qXf0_9kNJZ4&s=10'),
(4, 'Tempo Traveller', 'A tempo traveller is a luxury minibus with a seating capacity ranging between 12 to 30 people. Tempo travellers come with various features for a comfortable journey of passengers.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTog61VZgFtQdm3YJSDP2Bm2fQvFkHPW-Vj9w&usqp=CAU');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `id` int NOT NULL,
  `user_id` text NOT NULL,
  `name` text NOT NULL,
  `amount` text NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`id`, `user_id`, `name`, `amount`, `description`) VALUES
(1, '3', 'Athirapilly Trip Food Expenses', '150', 'Food expense is too High in this area. Per lunch it costs around 150'),
(2, '2', '24-05_22', '600', 'Food , entertainment,');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `map_link` text NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `description`, `map_link`, `image`) VALUES
(1, 'Radisson Blu Kochi', 'S.A Road, Junction, Elamkulam, Kochi, Kerala 682020•0484 412 9999', 'https://goo.gl/maps/EENJeKxS41jgdfuJA', 'https://lh3.googleusercontent.com/p/AF1QipPXj88pWqOPlfpM4A4YGRD85MVl-hLsBAHUDmDR=w296-h202-n-k-rw-no-v1'),
(2, 'Treebo Trend Time Square', 'Set 5 km from Ernakulam Shiva Temple, this polished hotel is 7 km from Kerala Folklore Museum and 12 km from Mattancherry Palace', 'https://g.page/time-square-hotel-kochi?share', 'https://images.trvl-media.com/hotels/3000000/2300000/2293000/2292952/bcc317e4_z.jpg'),
(3, 'Rainforest resort Athirapilly', 'Within the Sholayar Reserve Forest, this tranquil jungle lodge on a bend in the Chalakkudy River overlooks the Athirappilly Waterfalls and is 13 km from Peringalkuthu Dam.', 'https://www.google.com/travel/hotels/s/vyiCY3ikF67zxN2J8', 'https://lh5.googleusercontent.com/p/AF1QipN-oywWX7ZYIHU-kwwF4-s8bNNrx3nPKGWX7BZK=w393-h220-k-no');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` int NOT NULL,
  `description` text NOT NULL,
  `user_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `description`, `user_id`) VALUES
(1, 'nyc', '1');

-- --------------------------------------------------------

--
-- Table structure for table `spot`
--

CREATE TABLE `spot` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `district_id` text NOT NULL,
  `location_name` text NOT NULL,
  `location_desc` text NOT NULL,
  `map_link` text NOT NULL,
  `image` text NOT NULL,
  `pump_location` text NOT NULL,
  `atm_link` text NOT NULL,
  `spot_type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `spot`
--

INSERT INTO `spot` (`id`, `name`, `district_id`, `location_name`, `location_desc`, `map_link`, `image`, `pump_location`, `atm_link`, `spot_type`) VALUES
(1, 'Athirappilly Water Falls', '0', 'Athirappilly', 'Athirappilly is a first grade Grama Panchayath with 489.00 km² area in Chalakudy Taluk, Thrissur district in Kerala, India. It is located 60 km from Thrissur city, 70 km northeast of Kochi city, 55 km northeast of Cochin International Airport, and 30 km from Chalakudy town.', 'https://goo.gl/maps/denN7wF6phQ3YV2o8', 'https://irisholidays.com/backend/web/tours/athirappilly-hill-waterfalls-1568550654.jpg', 'https://goo.gl/maps/q1Wyfkmoyj1j4Zt57', 'https://goo.gl/maps/VCp4PZ4YyEsgxa179', '1'),
(2, 'Vazachal Water Falls', '0', 'Vazachal', 'Forest trails & riverside lookouts around a landmark waterfall known for its monsoon-season surges.\r\nAddress: 8H2V+H28, Athirapilly road, Pariyaram, Kerala 680724', 'https://goo.gl/maps/NaW4HnhmQzet5qPM8', 'https://3.imimg.com/data3/RC/XO/MY-17015482/wp-content-uploads-2014-12-vazhachal-2-450x300-500x500.jpg', 'https://goo.gl/maps/sgs4dAL7sdJkn9vB8', 'https://goo.gl/maps/wAVMGT6szCXtGyFR9', '1'),
(3, 'Pattathi Para', '0', 'Thrissur', 'nyc Place To Visit. Nyc Atmosphere And Cool Breeze', 'https://goo.gl/maps/uyqwFUK46ZRUtZQz7', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3fZGaWtIaW7iHBgsoYJggnHOvRhGEDMQ0FA&usqp=CAU', 'https://goo.gl/maps/KR8SXefYX87Mi65k7', 'https://goo.gl/maps/wx8KzsfpCSAHnV2t5', '1'),
(4, '900 Kandi', '1', 'Wayanad', 'In the deep reaches of Wayanad in Kerala, there is a lush green paradise where adventure meets serenity. With its towering trees, rolling hills and canopy', 'https://www.google.com/search?q=900+kandi&source=lmns&bih=720&biw=393&client=ms-android-xiaomi&prmd=imnv&hl=en&sa=X&ved=2ahUKEwic3Lfyr7D4AhWx0HMBHV1CAscQ_AUoAHoECAAQAw#trex=m_t:lcl_akp,rc_f:rln,rc_ludocids:6912560872657214997,ru_gwp:0%252C7,ru_lqi:Cgk5MDAga2FuZGlIzMfLx8CugIAIWhMQABABGAAYASIJOTAwIGthbmRpkgESdG91cmlzdF9hdHRyYWN0aW9u,ru_phdesc:scis2BRp_gE,trex_id:izlOme&lpg=cid:CgIgAQ%3D%3D', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlX-vboEiYNS9cKSazlsU5-NPnumOOPIV8qQ&usqp=CAU', 'https://www.google.com/search?q=900+kandi+near+by+petrol+pump&client=ms-android-xiaomi&bih=466&biw=393&hl=en&sxsrf=ALiCzsb-0i41F0l8VnLG_K4mh7sOqiTBHg%3A1655327668819&ei=tEuqYsXMMZDa4-EPnsCa2Ao&hotel_occupancy=2&oq=900+kandi+near+by+&gs_lcp=ChNtb2JpbGUtZ3dzLXdpei1zZXJwEAEYAjIFCCEQoAEyBQghEKABMgUIIRCgATIFCCEQkgMyBQghEJIDMgUIIRCSAzIFCCEQkgMyBQghEJIDOgcIABBHELADOgQIIxAnOgcIIRAKEKABOggIABCABBDJAzoECCEQFToICCEQHhAWEB1KBAhBGABQugFY-RxgvCVoAHABeACAAbcDiAHBGJIBCTAuNy4zLjIuMZgBAKABAcgBCMABAQ&sclient=mobile-gws-wiz-serp#trex=m_t:lcl_akp,rc_f:rln,rc_ludocids:82383071324303891,ru_gwp:0%252C7,ru_lqi:Ch05MDAga2FuZGkgbmVhciBieSBwZXRyb2wgcHVtcDGA7gIAAAAAAJIBC2dhc19zdGF0aW9uqgEbEAEqFyITbmVhciBieSBwZXRyb2wgcHVtcCgA,trex_id:UtAPZe', 'https://www.google.com/search?client=ms-android-xiaomi&hl=en&hotel_occupancy=2&sxsrf=ALiCzsYUXp50xO-MYSlPhXCrChpj58u9IA:1655327711879&q=State+Bank+of+India+ATM+Chowannur,+Kerala&ludocid=11970303252489817759&gsas=1&client=ms-android-xiaomi&ibp=gwp;0,7&lsig=AB86z5We0tUqiQlu50m0do0g3B0-&lqi=ChU5MDAga2FuZGkgbmVhciBieSBhdG1I3tCwzt-rgIAIWiMQABABEAIQAxAEGAQiFTkwMCBrYW5kaSBuZWFyIGJ5IGF0bZIBA2F0baoBExABKg8iC25lYXIgYnkgYXRtKAA&phdesc=7Uf57NWp6hw&sa=X&ved=2ahUKEwiQjoOisLD4AhWZ-zgGHWDvDuYQkbkFegQIChAD&biw=393&bih=720&dpr=2.75', '1'),
(5, 'Vilangan Hill', '0', 'Thrissur', 'Vilangan Hills (Malayalam: വിലങന്‍ കുന്നു) is a hillock located in Adat Panchayat, near Thrissur city of Kerala state in India. The hill gives a panoramic view of Thrissur city and Thrissur Kole Wetlands from the top. The hill was referred as an Oxygen Jar of Thrissur city.', 'https://www.google.com/maps/place/Vilangan+Hills/@10.5573849,76.1685922,17z/data=!3m1!4b1!4m5!3m4!1s0x3ba7ec22e72430df:0xbe8df07cfe655dc0!8m2!3d10.5573849!4d76.1685922', 'https://live.staticflickr.com/1079/538532009_5774049325_c.jpg', 'https://goo.gl/maps/KR8SXefYX87Mi65k7', 'https://goo.gl/maps/wx8KzsfpCSAHnV2t5', '1'),
(9, 'Vilangan Hills', '0', 'Thrissur', 'Vilangan Hills (Malayalam: വിലങന്‍ കുന്നു) is a hillock located in Adat Panchayat, near Thrissur city of Kerala state in India. The hill gives a panoramic view of Thrissur city and Thrissur Kole Wetlands from the top. The hill was referred as an Oxygen Jar of Thrissur city.', 'https://maps.app.goo.gl/HHBpFbdxyrkqMnhJ9', 'https://images.app.goo.gl/hihbRv5WXGD912s77', 'https://maps.app.goo.gl/iVUU7m6LHafv3Jd46', 'https://maps.app.goo.gl/E6p3vmvjooTnpTkL8', '1'),
(10, 'Chembra Peak', '1', 'Wayanad', 'Chembra Peak is a mountain in the state of Kerala, India, with an elevation of 2,100 m above sea level.', 'https://g.co/kgs/dojEFm', 'https://lh5.googleusercontent.com/p/AF1QipP67mGO8LcqHsz7qB4s4gjicTJiTZZFbiJyY3zA=w888-h660-n-k-no', 'https://g.co/kgs/mCa5rs', 'https://g.co/kgs/G1Ugdm', '1'),
(11, 'Chembra Peak', '1', 'Wayanad', 'Chembra Peak is a mountain in the state of Kerala, India, with an elevation of 2,100 m above sea level.', 'https://g.co/kgs/dojEFm', 'https://lh5.googleusercontent.com/p/AF1QipP67mGO8LcqHsz7qB4s4gjicTJiTZZFbiJyY3zA=w888-h660-n-k-no', 'https://g.co/kgs/mCa5rs', 'https://g.co/kgs/G1Ugdm', '1'),
(12, 'Shakthan Thampuran Palace', '0', 'Thrissur', 'Shakthan Thampuran Palace is situated in City of Thrissur in Kerala state, India. It is named as Vadakkekara Palace, was reconstructed in Kerala-Dutch style in 1795 by Ramavarma Thampuran of the erstwhile Princely State of Cochin, well as Sakthan Thampuran is preserved by Archaeological Department.', 'https://g.co/kgs/PCnRCf', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv1lbwucOZE4ibDtuFXNMKbKj2aRBHtlc4Mw&usqp=CAU', 'https://g.page/trichur-petrol-sales-and-service?share', 'https://g.co/kgs/H5QHxZ', '1'),
(13, 'Kurumbalakotta Mala', '1', 'Wayanad', 'Kurumbalakotta is a monolith hillock located in Wayanad district at Kerala, India. Situated at 3,220 feet above sea level, the hill gives a splendid view of the scenery spread around. Geographically, the mountain is a part of the Deccan plateau and is in the centre of Wayanad.', 'https://g.co/kgs/qipe2o', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv1lbwucOZE4ibDtuFXNMKbKj2aRBHtlc4Mw&usqp=CAU', 'https://g.co/kgs/3HQ68x', 'https://g.co/kgs/NBLZ4e', '0'),
(14, 'Kurumbalakotta', '1', 'Wayanad', 'Kurumbalakotta (Malayalam: കുറുമ്പാലക്കോട്ട) is a hill 20Km west of Kalpetta in Wayanad district, Kerala. It is a monolith hillock in Kerala. It rises to 991 m (3251.31234 feet) above sea level. It is situated in the centre of Wayanad and also a part of Deccan plateau and the confluence of Western Ghats and Eastern Ghats.', 'https://g.co/kgs/qipe2o', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv1lbwucOZE4ibDtuFXNMKbKj2aRBHtlc4Mw&usqp=CAU', 'https://g.co/kgs/3HQ68x', 'https://g.co/kgs/NBLZ4e', '0'),
(17, 'Kurumbalakotta', '1', 'Wayanad', 'is a hill 20Km west of Kalpetta in Wayanad district, Kerala. It is a monolith hillock in Kerala. It rises to 991 m (3251.31234 feet) above sea level', 'https://g.co/kgs/qipe2o', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv1lbwucOZE4ibDtuFXNMKbKj2aRBHtlc4Mw&usqp=CAU', 'https://g.co/kgs/3HQ68x', 'https://g.co/kgs/NBLZ4e', '1'),
(18, 'Chuttipara', '3', 'Pathanamthitta', 'Chuttippara, a 200 ft. rock cluster is one of the tourist spots where you can get a panoramic view of Pathanamthitta town. Atop the hill, there is a temple dedicated to Lord Shiva, Harihara Mahadeva Temple.', 'https://g.co/kgs/YoJAqa', 'https://lh5.googleusercontent.com/p/AF1QipPqANrLokbDK8Q7zdjpu8TC5Qghr_cbuTl3OEOD=w888-h660-n-k-no', 'https://g.co/kgs/AjzRi3', 'https://g.co/kgs/7xSE2z', '1'),
(19, 'Yyy', '0', 'Hhbb', 'Bbbb', 'Bbb', 'Jjzb', 'Bb', 'Bbb', '2'),
(20, 'Jhzhshb', '0', 'Hhhh', 'Hhhh', 'Hhh', 'Hhhh', 'Hhhh', 'Hhh', '2'),
(21, 'Star Forest', '0', 'Thrissur', 'A well manged and labelled star forest. According to hindu mythology there are 27 birth star that falls on the path of the moon when the moon completes a revolution around the earth (it takes 27 days).', 'https://g.co/kgs/1uJwUN', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8Bo47lZ2THN7QjolwHIvDlypKwT_AX6fmDw&usqp=CAU', 'https://g.co/kgs/D6xybT', 'https://g.co/kgs/EWrT3R', '4'),
(22, 'Elephant Sanctuary', '0', 'Thrissur', 'many by front and back legs simultaneously.\nMany are chained so tightly they can hardly move, others constantly rock and sway due to stress.\nTerrible to witness in the 21st century.', 'https://g.co/kgs/D8hQ22', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUdrNHDYXHilsi9vP9qUflEmMwX6RjKfBNCw&usqp=CAU', 'https://g.co/kgs/zH511G', 'https://g.co/kgs/2yKveo', '4'),
(23, 'Elephant Sanctuary', '0', 'Thrissur', 'many by front and back legs simultaneously.\nMany are chained so tightly they can hardly move, others constantly rock and sway due to stress.\nTerrible to witness in the 21st century.', 'https://g.co/kgs/D8hQ22', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUdrNHDYXHilsi9vP9qUflEmMwX6RjKfBNCw&usqp=CAU', 'https://g.co/kgs/zH511G', 'https://g.co/kgs/2yKveo', '4'),
(24, 'Muthanga Forest', '1', 'Wayanad', 'One can experience the nature at its own color here. Traveling through this road would be a cheering feel to mind and soul.', 'https://g.co/kgs/kfBuWy', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq1o7RUYRzsNq8PlMkEy8JJ09fRiV9FU2_cw&usqp=CAU', 'https://g.co/kgs/zH511G', 'https://g.co/kgs/4inUfr', '4'),
(25, 'Pulpally Forest', '1', 'Wayanad', 'Its is a beautiful forest range in Wayanad. This stretch is famous for a Friendly Wild Elephant called Maniyan. He is so friendly that people and even childrens used to Feed him fruits.', 'https://g.co/kgs/RV5YTR', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTb71rr8a-rD_K_XN0l4200ANj7_XRTAmEQfwxBgZl5piVBX7I_tPVflqB_&s=10', 'https://g.co/kgs/Mf1dwh', 'https://g.co/kgs/yxSHvL', '4'),
(26, 'Teak Forest', '2', 'Palakkad', 'Teak is a tropical hardwood tree species in the family Lamiaceae. It is a large, deciduous tree that occurs in mixed hardwood forests. Tectona grandis has small, fragrant white flowers arranged in dense clusters at the end of the branches. These flowers contain both types of reproductive organs.', 'https://g.co/kgs/tTeSHj', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhNyOQqCMpOklfERZY-QqKQfkiNiWs5u5fwJn_vz3C1l-utWg1azkHDTEU&s=10', 'https://g.co/kgs/tq5DWJ', 'https://g.co/kgs/SChyXT', '4'),
(27, 'Forest Tribunal', '2', 'Palakkad', 'Its a Beautiful Forest Range.', 'https://g.co/kgs/i1C4fm', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5E7e8zB8lmfkF-sgnpMF-xUVdnQyZSdLNeg&usqp=CAU', 'https://g.co/kgs/LUEURa', 'https://g.co/kgs/ziaKG5', '4'),
(28, 'Goodrical Forest Range', '3', 'Pathanamthitta', 'It is the Goodrical range which is also home to the most number of wild animals in the Ranni forest division. Tiger, leopard, tuskers, wild bison, barking deer, sambar deer, langur, and lion tailed macaque are some of the wild animals which are spotted at the 565 kilometre square forest range.', 'https://g.co/kgs/ADZA9t', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdUSwGi_Z8gvLyNIym4yyfbkWlHeMAuMuLAA&usqp=CAU', 'https://g.co/kgs/qsdYBQ', 'https://g.co/kgs/GAowKg', '4'),
(29, 'Ranni Forest Division', '3', 'Pathanamthitta', 'The Ranni Forest Division in Kerala, India, was constituted on 7 July 1958, comprising the Ranni, Vadasserikkara, and Goodrical ranges, with its headquarters at Ranni. It covers the parts of Konni reserve forest and the reserves of Ranni, Goodrical, Rajampara, Karimkulam, Kumaramperoor, Valiyakavu, and Schettakkal. It covers an area of 1,059 square kilometres (409 sq mi)', 'https://g.co/kgs/DZ2TKp', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSosCIFVmG3H-Ecq6xfZbfXh2BhV5HMEizd9Q&usqp=CAU', 'https://g.co/kgs/Db7GqM', 'https://g.co/kgs/X4VG4L', '4'),
(30, 'Alleppey Pine Forest', '4', 'Alappuzha', 'Good place to sit and enjoy. Brick pavement made the place looks awesome. It is very calm and cool area even in noon time also.', 'https://g.co/kgs/B2Yw2y', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWkKMRE3183JDmvkIZI4SLgKz6pfLmqHq-MA&usqp=CAU', 'https://g.co/kgs/CStCfx', 'https://g.co/kgs/gWZkUb', '4'),
(31, 'NAD Forest', '5', 'Ernakulam', 'Forest\nReviewed September 9, 2017\nIts around 65 km away form Ernakulam town and 10 km from Kothmanaglam Junction. Roads are good to reach there. we can enjoy the Boat service Rs 150 per person. trucking to old bhoothathan kettu , its a great experience to know the forest and nature.', 'https://g.co/kgs/PDihAi', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSxpNhsOpOaO7N4KaPDPi_pODcbkhJqFrLiw&usqp=CAU', 'https://g.co/kgs/JQpDDN', 'https://g.co/kgs/HYDoUc', '4'),
(32, 'City Forest', '5', 'Ernakulam', 'Recreational\nCity Forest Park, which is located on the banks of the Hindon, has walking trails, cycle tracks,, horse-riding facility and guided tours for visitors with open gypsy/jeep rides.The project, which is spread across 175 acres,and is offering the public an opportunity to try all rides .', 'https://g.co/kgs/BKsu59', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvxw33Lm8HyzmcOfAOHZShlqbadem7ABV5Yg&usqp=CAU', 'https://g.co/kgs/kv6Fow', 'https://g.co/kgs/9nR2n2', '4'),
(33, 'Reserve Forest Idukki', '6', 'Idukki', 'Idukki Wildlife Sanctuary extends over the Thodupuzha and Udumpanchola Taluks of Idukki District, spreading over 77 sq. km and is about 450 - 748 m above sea level. The Idukki Reservoir formed by three dams - Cheruthoni, Idukki and Kulamav - extends to 33 sq. km.', 'https://g.co/kgs/4m58NF', 'https://lh5.googleusercontent.com/p/AF1QipP3eqiyG4RGO8Qx-UYnD7Bub0dn3heNAbb8NbWX=w888-h660-n-k-no', 'https://g.co/kgs/rGNhq2', 'https://g.co/kgs/F4fHhg', '1'),
(34, 'Reserve Forest Idukki', '6', 'Idukki', 'Idukki Wildlife Sanctuary extends over the Thodupuzha and Udumpanchola Taluks of Idukki District, spreading over 77 sq. km and is about 450 - 748 m above sea level. The Idukki Reservoir formed by three dams - Cheruthoni, Idukki and Kulamav - extends to 33 sq. km.', 'https://g.co/kgs/4m58NF', 'https://lh5.googleusercontent.com/p/AF1QipP3eqiyG4RGO8Qx-UYnD7Bub0dn3heNAbb8NbWX=w888-h660-n-k-no', 'https://g.co/kgs/rGNhq2', 'https://g.co/kgs/F4fHhg', '8'),
(35, 'Reserved Forest', '6', 'Idukki', 'Idukki Wildlife Sanctuary extends over the Thodupuzha and Udumpanchola Taluks of Idukki District, spreading over 77 sq. km and is about 450 - 748 m above sea level. The Idukki Reservoir formed by three dams - Cheruthoni, Idukki and Kulamav - extends to 33 sq. km. ', 'https://g.co/kgs/4m58NF', 'https://lh5.googleusercontent.com/p/AF1QipP3eqiyG4RGO8Qx-UYnD7Bub0dn3heNAbb8NbWX=w888-h660-n-k-no', 'https://g.co/kgs/rGNhq2', 'https://g.co/kgs/F4fHhg', '4'),
(36, 'Chokramudi', '6', 'https://g.co/kgs/NmaZh1', 'Chokramudi Peak is one of the highest peaks of Munnar that attracts a wide range of trekkers. Being one of the highest peaks in Munnar, with an altitude of around 7,200 ft, Chokarmudi peak gives a spectacular view of the tea plantations, Anamudi peak, and Idukki Dam.', 'https://g.co/kgs/NmaZh1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZJc0iaNT68CWvMgm9xHCke_KemaPUjCmY8afwWMSNqR77rhzrOglqlVY&s=10', 'https://g.co/kgs/zTHDdM', 'https://g.co/kgs/KDkBv2', '4');

-- --------------------------------------------------------

--
-- Table structure for table `spot_type`
--

CREATE TABLE `spot_type` (
  `id` int NOT NULL,
  `type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `spot_type`
--

INSERT INTO `spot_type` (`id`, `type`) VALUES
(1, 'Trucking'),
(2, 'Historical'),
(3, 'Adventure'),
(4, 'Forest');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `name` text NOT NULL,
  `phone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `name`, `phone`) VALUES
(1, 'admin@gmail.com', '000000', 'Admin', '9876543210'),
(2, 'devikapnair222@gmail.com', 'devika', 'Devika', '7594882837'),
(3, 'amal@gmail.com', '000000', 'Amal Das V S', '9961498198'),
(4, 'deminadavis03@gmail.com', 'demina', 'Demina', '9562261966'),
(5, 'anitta@gmail.com', 'anu', 'Anitta', '8593858454'),
(6, 'aleena@gmail.com', '1233', 'Aleena', '9846906567'),
(7, 'amaldas.pdas@gmail.com', '123456', 'Amal Das V S', '9961498198');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `id` int NOT NULL,
  `user_id` text NOT NULL,
  `spot_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `user_id`, `spot_id`) VALUES
(2, '', '2'),
(11, '1', '4'),
(20, '3', '12'),
(32, '2', '10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `spot`
--
ALTER TABLE `spot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `spot_type`
--
ALTER TABLE `spot_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `spot`
--
ALTER TABLE `spot`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `spot_type`
--
ALTER TABLE `spot_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
