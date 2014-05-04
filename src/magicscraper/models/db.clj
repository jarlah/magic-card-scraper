(ns magicscraper.models.db
	(:require 
		[clojure.java.jdbc :as sql] 
		[magicscraper.models.fetcher :as fetcher])
	(:import java.sql.DriverManager))

(def db
	{:classname "org.sqlite.JDBC",
		:subprotocol "sqlite",
		:subname "db.sq3"})

(defn read-cards []
	(sql/with-connection
		db
		(sql/with-query-results res
			["SELECT * FROM magicscraper"]
			(doall res))))

(defn create-cards-table []
	(sql/with-connection
		db
		(sql/create-table
			:magicscraper
			[:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
			[:name "TEXT"]
			[:cardid "TEXT"]
			[:color "TEXT"]
			[:cardset "TEXT"]
			[:rarity "TEXT"])))

(defn save-card [name cardid color cardset rarity]
	(sql/with-connection
		db
		(sql/insert-values
			:magicscraper
			[:name :cardid :color :cardset :rarity]
			[name cardid color cardset rarity])))

(defn populate-cards []
	(for [card (fetcher/select-cards)]
		(save-card 
			(get card :name) 
			(get card :id) 
			(get card :color) 
			(get card :set) 
			(get card :rarity))))