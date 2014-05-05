(ns magicscraper.routes.home
  (:require [compojure.core :refer :all]
            [magicscraper.views.layout :as layout]
            [magicscraper.views.cards :as cards]
            [hiccup.form :refer :all]
            [magicscraper.models.db :as db]))

(defn home []
  (layout/common 
  	[:h1 "Magic Card Database"]
  	(cards/show-cards (db/read-cards))))

(defn card [id]
	(layout/common
		[:h1 (str "Viewing card with id " id)]))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/card/:id" [id] (card id)))
