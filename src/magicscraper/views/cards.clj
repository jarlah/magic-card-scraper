(ns magicscraper.views.cards
  (:require [hiccup.form :refer :all]))

(defn show-cards [cards]
	[:div.checklist
	(for [{:keys [id name cardid color cardset rarity artist]} cards]
		[:div.card
			[:img {:src (str "img/" cardid ".jpg") :width "200px" :height "284px"}]
			[:a {:href (str "card/" cardid)} [:span name]]
			[:p color]
			[:p cardset]
			[:p rarity]
			[:p artist]])])