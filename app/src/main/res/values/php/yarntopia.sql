-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2024 at 03:17 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yarntopia`
--

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `idBerita` int(11) NOT NULL,
  `judulBerita` varchar(100) DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `gambar` varchar(255) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `idPembuat` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`idBerita`, `judulBerita`, `deskripsi`, `gambar`, `tanggal`, `idPembuat`) VALUES
(1, 'How to Hold Your Crochet Hook', 'Crochet newbies, learn how to comfortably grip your hook for those upcoming stitch projects! This guide explores different holding techniques for a successful crochet journey.', 'https://cdn.pixabay.com/photo/2023/08/20/17/00/crochet-8202792_1280.jpg', '2023-10-23', 'carmenCr'),
(2, 'Yarn n\' Cat', 'Claws vs. Crafts! Can yarn and feline friends co-exist? This piece explores the hilarious struggles (and purrfect moments) of crocheting with a cat in your life.', 'https://cdn.pixabay.com/photo/2024/03/12/18/01/ai-generated-8629283_1280.png', '2024-01-01', 'wLois'),
(3, 'Tips for Choosing Yarn', 'Unveiling the Perfect Skein! Our guide unravels the secrets to choosing the best yarn for your next knitting or crochet masterpiece. From fiber types to weight and care, learn how to select yarn that brings your project to life!', 'https://media.istockphoto.com/id/842959826/id/foto/clews-berwarna-wol-di-rak-rak-toko.jpg?s=2048x2048&w=is&k=20&c=BdlcI149I0ctB8F2o92AFQpMurGHecAlinnmBgVIWug=', '2023-07-06', 'fischer01'),
(4, 'This Elementary School Bus Driver Crocheted a Gift for Every Student! (Heartwarming!)', 'A well-liked elementary school bus driver named Bob surprised his students with crocheted gifts! Each student received a personalized item, like a stuffed animal or scarf.  People online are loving this story of Bob\'s kindness and thoughtfulness towards h', 'https://t4.ftcdn.net/jpg/00/87/93/75/360_F_87937582_30Np7ohTEVRr1iatMH5i10QF3OITEOg8.jpg', '2024-02-21', 'cindy'),
(5, 'Move Over, Pumpkin Spice: Cozy Crochet is the New Fall Fashion Must-Have', 'Forget pumpkin spice lattes, this fall\'s hottest trend is cozy crochet fashion. From designer runways to social media, crochet is everywhere. It\'s comfortable, stylish, and perfect for crisp autumn weather. You can even make your own unique pieces, making', 'https://i.etsystatic.com/6612469/r/il/2fb4be/5507397602/il_fullxfull.5507397602_9cit.jpg', '2023-10-31', 'carlaa');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `idPembaca` varchar(45) NOT NULL,
  `idBerita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`idPembaca`, `idBerita`) VALUES
('a', 1),
('a', 3),
('a', 4),
('carlaa', 4),
('carlaa', 5),
('cindy', 1),
('cindy', 2);

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

CREATE TABLE `page` (
  `idPage` int(11) NOT NULL,
  `idBerita` int(11) NOT NULL,
  `kontenBerita` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `page`
--

INSERT INTO `page` (`idPage`, `idBerita`, `kontenBerita`) VALUES
(1, 1, 'How to Hold Your Crochet Hook: Mastering the Grip for Stitch Success\r\nEver looked at a beautiful crochet project and thought, \"I wish I could make that!\" Well, the first step on your crocheting adventure is mastering the grip of your hook. Don\'t worry, it\'s not as complicated as it might seem! This guide will explore two common holding techniques to ensure comfort and control as you create.\r\n\r\nThe Tools of the Trade:\r\n\r\nBefore we dive in, let\'s get familiar with your crochet hook. Most hooks have a designated grip area, often with a flattened section for your thumb. You\'ll also find the hook itself, used for grabbing yarn, and the working area, where your stitches rest.'),
(1, 2, 'Yarn and Cat: A Hilarious (and Purrfect) Coexistence\r\nAh, the undeniable allure of a soft, colorful ball of yarn. For crocheters, it\'s the promise of cozy blankets, adorable amigurumi, and endless creative expression. But for our feline companions, yarn transforms into the ultimate pouncing toy, a feathery nemesis begging to be wrestled. So, can yarn and cats ever truly coexist? The answer, my friends, is a resounding yes, albeit with a healthy dose of laughter and maybe a few snagged stitches.\r\n\r\nThe Allure of the Wiggly Wonder:\r\n\r\nFrom a cat\'s perspective, yarn is a symphony of textures and movement. It wiggles, it dangles, it begs to be batted and chased. The sight of a human working with yarn can trigger a primal hunting instinct, sending your kitty into a playful frenzy. Don\'t be discouraged, though! This playful energy can be a source of amusement, especially when captured in a perfectly timed photo (think yarn ball mid-air, kitty leaping with a look of pure determination).'),
(1, 3, 'Tips for Choosing Yarn: Unveiling the Perfect Skein for Your Next Project\r\nSo you\'ve found the perfect knitting or crochet pattern, needles or hook in hand, and excitement buzzing. But before you dive in, there\'s one crucial step: choosing the yarn. Don\'t worry, this isn\'t a decision to be made with trepidation! With a few handy tips, you can select the perfect yarn to bring your project to life.\r\n\r\nFiber Frenzy: Knowing Your Materials\r\n\r\nYarn comes in a dazzling array of fibers, each with its own unique properties. Here\'s a quick breakdown of some popular choices:\r\n\r\nNatural Fibers:\r\n\r\nWool: Warm, cozy, and excellent for winter projects. However, it can be itchy for some and requires special care.\r\nCotton: Breathable and easy to care for, making it ideal for garments and amigurumi (stuffed toys).\r\nLinen: Strong, lustrous, and perfect for summer projects like bags and dishcloths.\r\nSynthetic Fibers:\r\n\r\nAcrylic: Affordable, soft, and machine-washable, making it a great choice for beginners.\r\nNylon: Strong, durable, and often used in sock yarn for its ability to withstand wear and tear.'),
(1, 4, 'VIRAL: Kindness knows no bounds, and this elementary school bus driver is proving just that! Bob, a beloved driver for Charity School in Washington, DC, has melted hearts across the internet after it was revealed that he crocheted a personalized gift for each of his students.\r\n\r\nThe heartwarming story came to light after Lily, a parent of one of the students on Bob\'s route, shared a photo on social media of the adorable crocheted gifts. The picture quickly went viral, with many praising Bob\'s thoughtful gesture.'),
(1, 5, 'Move over, pumpkin spice. This fall, it\'s all about warm, textured comfort, and crochet is emerging as the must-have fashion trend. Gone are the days of crochet being relegated to grandma\'s blankets. Today\'s crochet pieces are stylish, statement-making, and perfect for those cool autumn days.\r\n\r\nFall has always been a time for warm colors, cozy textures, and layering up. But this year, a surprising contender is dethroning the pumpkin spice latte as the season\'s hottest trend: crochet.\r\n\r\nGone are the days when crochet was solely associated with your grandma\'s afghan blankets.  A wave of innovation and fresh design has swept through the world of yarn, transforming crochet into a fashion statement.\r\n\r\nFrom runways to everyday wear, crochet is taking center stage:\r\n\r\nHigh-fashion designers are incorporating crochet into their collections, with everything from delicate sweaters to bold, chunky dresses gracing the runways.\r\nSocial media is abuzz with crocheted creations, with influencers and everyday people sharing their love for the craft. From cozy hats and scarves to trendy bags and vests, crochet offers endless possibilities for self-expression.\r\nThe appeal of crochet lies in its unique combination of comfort and style. The soft, textured yarns create pieces that are warm and inviting, perfect for those crisp fall days. Yet, crochet can also be surprisingly versatile, with intricate patterns and modern designs that can elevate any outfit.'),
(2, 1, 'Holding Styles:\r\n\r\nThe Pencil Grip: This method feels familiar to anyone who\'s ever held a writing utensil. Pinch the grip area between your thumb and index finger, similar to how you\'d hold a pencil. Your remaining fingers can curl naturally around the handle for support. The hook should be angled slightly towards you, allowing you to see your stitches clearly.\r\n\r\nThe Knife Grip: Imagine holding a butter knife for spreading. This grip positions your hand on top of the hook, with your thumb resting on the flattened grip area and your index finger on the opposite side. Again, the hook should be angled towards you, and your other fingers can comfortably wrap around the handle.\r\n\r\nFinding Your Fit:\r\n\r\nThere\'s no single \"correct\" way to hold a crochet hook. Experiment with both the pencil and knife grips and see which feels most comfortable for you. Remember, you want a loose but controlled grip that allows for easy manipulation of the hook and yarn.'),
(2, 2, 'Tips for Taming the Yarn Monster:\r\n\r\nDesignated Playtime: Channel your cat\'s energy into a separate playtime session with a dedicated \"yarn toy.\" This could be a store-bought catnip-filled yarn mouse or a simple braid made from leftover yarn scraps.\r\nPut it Away: When you\'re not actively crocheting, store your yarn in a closed container or basket. Out of sight, (mostly) out of mind!\r\nStrategic Placement: Crochet in a cat-free zone, if possible. This could be a closed room or a designated crafting corner with limited access for feline inspectors.'),
(2, 3, 'Weighty Matters: Understanding Yarn Thickness\r\n\r\nYarn weight refers to its thickness, and it\'s crucial for achieving the right gauge (stitches per inch) for your pattern. Most yarn labels will have a weight category (0-7) or a descriptive term like \"worsted\" or \"bulky.\" Here\'s a general guide:\r\n\r\nLighter weight yarns (0-3): Ideal for delicate garments, lace shawls, and intricate amigurumi.\r\nMedium weight yarns (4-5): The most versatile option, perfect for sweaters, blankets, and many other projects.\r\nHeavy weight yarns (6-7): Great for cozy winter accessories, chunky blankets, and fast-crochet projects.'),
(2, 4, '\"Bob is the most amazing bus driver!\" said Lily in their post. \"He goes above and beyond for his students every day, and this is just another example of his kindness. My child was so excited to receive their own special gift!\"\r\n\r\nThe crocheted gifts reportedly included a variety of items, such as stuffed animals, hats, and scarves. Each gift was personalized with the student\'s name, making it an extra special touch.\r\n\r\n'),
(2, 5, 'What\'s Fueling the Crochet Craze?\r\n\r\nSeveral factors are contributing to the surge in popularity of crocheted fashion:\r\n\r\nA Shift Towards Comfort: After years of fast fashion trends, consumers are seeking comfort and quality in their clothing. Crochet offers a unique blend of warmth, softness, and a handmade aesthetic that provides a sense of security and well-being – perfect for those chilly fall days.\r\nVersatility with a Modern Edge: Crochet is no longer confined to simple squares and granny stitch blankets. Designers are pushing the boundaries with intricate patterns, bold color combinations, and unexpected silhouettes. From delicate, lacy tops to chunky, statement sweaters and dresses, crochet offers an incredibly versatile range of options to suit any personal style.\r\nThe Rise of Slow Fashion and DIY: Consumers are increasingly interested in sustainable practices and creating unique pieces that reflect their individuality. Crochet aligns perfectly with this trend. It\'s a relatively easy craft to learn, with countless online tutorials and beginner-friendly patterns available. People can create personalized, one-of-a-kind pieces that express their creativity and avoid the mass-produced feel of fast fashion.\r\nSocial Media Influence: Platforms like Instagram and TikTok are filled with a vibrant crochet community. Fashion influencers and everyday people are showcasing their love for the craft, inspiring others to take up crocheting or add these cozy pieces to their fall wardrobe.'),
(3, 1, 'Bonus Tips:\r\n\r\nRelax your hand: Tightly gripping the hook can lead to tension and fatigue. Let your hand be loose and flexible while maintaining control.\r\nPractice makes perfect: As with any new skill, practice is key. The more you crochet, the more comfortable your grip will become.\r\nWatch and learn: Many online resources and crochet tutorials showcase different holding techniques in action. [YouTube crochet tutorials] can provide valuable visual guidance.\r\nWith a comfortable grip on your crochet hook, you\'re well on your way to creating beautiful projects! So grab your yarn, choose your hook, and get ready to stitch your way to relaxation and creative fulfillment.'),
(3, 2, 'The Purrfect Moments:\r\n\r\nDespite the occasional yarn-related chaos, there\'s a certain charm to sharing your crafting space with a cat. Who can resist a fluffy supervisor curled up contentedly in your lap as you crochet? Sometimes, they even become unintentional (and very cute) yarn holders, their paws keeping the ball from rolling away.\r\n\r\nLiving with a Yarn-Loving Cat:\r\n\r\nCrocheting with a cat requires a bit of adaptation and humor. Embrace the occasional yarn-related mishap, invest in some good yarn storage solutions, and maybe even create a few designated \"kitty crochet toys.\"  Remember, the cuddles, purrs, and unexpected moments of amusement that come with sharing your crafting space with a feline friend are truly purrfect.'),
(3, 3, 'Considering Care: Keeping Your Project Looking Fresh\r\n\r\nDifferent fibers require different levels of care.  Pay attention to washing instructions on the yarn label.  Some yarns are machine-washable and dryable, while others require hand-washing and drying flat.  Consider the intended use of your project when choosing yarn care difficulty.\r\n\r\nBeyond the Basics: Choosing for Texture and Color\r\n\r\nNow for the fun part!  Once you\'ve considered fiber, weight, and care, explore the world of color and texture.  Solid colors create a classic look, while variegated or multicolored yarns add a touch of whimsy.  Experiment with textures like boucle (loopy) or tweed (flecked) for added visual interest.\r\n\r\nRemember, the best yarn for your project is the one that inspires you! Don\'t be afraid to experiment with different types and colors to find your perfect match. Happy stitching!'),
(3, 4, 'Since the story went viral, Bob has been inundated with praise from parents, teachers, and community members alike. Many have commended him for his dedication to his students and for going the extra mile to make them feel loved and appreciated.\r\n\r\nIn an interview, Bob said that he simply wanted to do something nice for his students. \"I love my students and I want them to know how much I care about them,\" he said. \"I crochet in my free time, so I thought it would be fun to make them each a little gift.\"\r\n\r\nBob\'s story is a heartwarming reminder of the power of kindness. His simple act of generosity has brought joy to the lives of his students and has inspired others to spread kindness in their own communities.'),
(3, 5, 'Beyond the Runway: A Lifestyle Trend\r\n\r\nThe influence of crochet isn\'t limited to clothing. Home decor is also experiencing a crocheted renaissance.  Cozy throws, chunky blankets, decorative wall hangings, and even crocheted baskets are gracing living spaces, adding a touch of handmade charm and warmth. This trend extends beyond aesthetics, creating a sense of comfort and inviting people to embrace the slower pace of fall.\r\n\r\nA Sustainable and Sustainable Trend\r\n\r\nCrochet also aligns with the growing interest in sustainable fashion.  Yarn can be made from natural fibers like wool and cotton, or even recycled materials like plastic bottles. Learning to crochet allows people to upcycle old clothes or yarn scraps, reducing textile waste and promoting a more eco-conscious approach to fashion.\r\n\r\nSo, this fall, consider swapping the pumpkin spice latte for a warm cup of tea and a cozy crochet project.  Embrace the slow fashion movement, express your creativity, and step out in style with a unique, handcrafted piece. The world of crochet awaits, offering warmth, comfort, and a chance to connect with the timeless art of yarn manipulation.'),
(4, 2, 'Sharing Your Crafting Space (with Reservations):\r\n\r\nLiving with a yarn-loving cat requires a bit of strategic planning and, let\'s face it, a good sense of humor. Here are some tips to navigate this delightful yet sometimes chaotic coexistence:\r\n\r\nSeparate But Equal: Allocate playtime specifically for your feline friend. Invest in dedicated \"kitty crochet toys\" – catnip-filled yarn mice, feathery wands, or even simple braids made from leftover yarn scraps – to channel their playful energy and distract them from your current project.\r\nOut of Sight, (Mostly) Out of Mind: When you\'re not actively crocheting, become a yarn ninja! Develop a system for swift and efficient yarn storage. Closed containers, baskets with lids, or even high shelves can become your best friends, keeping those tempting balls of yarn out of sight and (mostly) out of mind for your curious cat.\r\nFortress Crafting (Optional): If all else fails, consider creating a designated crafting zone with limited feline access. This could be a closed room or a corner cordoned off with a baby gate, offering you a temporary yarn sanctuary.');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `namaDepan` varchar(45) DEFAULT NULL,
  `namaBelakang` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `email`, `namaDepan`, `namaBelakang`) VALUES
('a', 'a', 'a', 'aa', 'aaa'),
('admin', 'admin', 'admin@gmail.com', 'admin', 'adminn'),
('b', 'b', 'b', 'bb', 'bbb'),
('c', 'c', 'c', 'cc', 'ccc'),
('carlaa', 'carla123', 'carla@gmail.com', 'Carla', 'Waller'),
('carmenCr', 'carmen123', 'carmen@gmail.com', 'Carmen', 'Craig'),
('cindy', 'cindy123', 'cindy@gmail.com', 'cindyy', 'yulindaa'),
('fischer01', 'fischer123', 'adrian@gmail.com', 'Adrian', 'Fischer'),
('wLois', 'lois123', 'Lois@gmail.com', 'Lois', 'Wu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`idBerita`),
  ADD KEY `fk_berita_users_idx` (`idPembuat`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`idPembaca`,`idBerita`),
  ADD KEY `fk_users_has_berita_berita1_idx` (`idBerita`),
  ADD KEY `fk_users_has_berita_users1_idx` (`idPembaca`);

--
-- Indexes for table `page`
--
ALTER TABLE `page`
  ADD PRIMARY KEY (`idPage`,`idBerita`),
  ADD KEY `fk_page_berita1_idx` (`idBerita`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `idBerita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `berita`
--
ALTER TABLE `berita`
  ADD CONSTRAINT `fk_berita_users` FOREIGN KEY (`idPembuat`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `fk_users_has_berita_berita1` FOREIGN KEY (`idBerita`) REFERENCES `berita` (`idBerita`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_berita_users1` FOREIGN KEY (`idPembaca`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `page`
--
ALTER TABLE `page`
  ADD CONSTRAINT `fk_page_berita1` FOREIGN KEY (`idBerita`) REFERENCES `berita` (`idBerita`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
