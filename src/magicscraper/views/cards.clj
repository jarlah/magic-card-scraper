(ns magicscraper.views.cards
  (:require [hiccup.form :refer :all]))

(defn show-cards [cards]
	[:ul.checklist
	(for [{:keys [id name cardid color cardset rarity artist]} cards]
		[:li
			[:a {:href (str "card/" cardid)} name]
			[:p color]
			[:p cardset]
			[:p rarity]
			[:p artist]])])