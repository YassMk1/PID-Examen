--
-- Index pour la table `artists`
--
ALTER TABLE `artists`
  ADD KEY `artists_agency_id` (`agency_id`);

--
-- Contraintes pour la table `artists`
--
ALTER TABLE `artists`
  ADD CONSTRAINT `artists_agency_id` FOREIGN KEY (`agency_id`) REFERENCES `agencies` (`id`);
