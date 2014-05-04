(ns magicscraper.routes.home
  (:require [compojure.core :refer :all]
            [magicscraper.views.layout :as layout]
            [hiccup.form :refer :all]
            [magicscraper.models.db :as db]))

(defn show-cards []
	[:ul.checklist
	(for [{:keys [id name cardid color cardset rarity]} (db/read-cards)]
		[:li
			[:a {:href (str "card/" id)} name]
			[:p cardid]
			[:p color]
			[:p cardset]
			[:p rarity]])])

(defn home []
  (layout/common 
  	[:h1 "Simple magic database"]
  	(show-cards)))

(defroutes home-routes
  (GET "/" [] (home)))
