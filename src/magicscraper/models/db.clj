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
			["SELECT * FROM cards"]
			(doall res))))

(defn read-sets []
	(sql/with-connection
		db
		(sql/with-query-results res
			["SELECT * FROM sets"]
			(doall res))))

(defn create-sets-table []
	(println "Creating sets table")
	(sql/with-connection
		db
		(sql/create-table
			:sets
			[:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
			[:name "TEXT"]
			[:description "TEXT"])))

(defn save-set [name description]
	(println (str "Saving set " name))
	(sql/with-connection
		db
		(sql/insert-values
			:sets
			[:name :description]
			[name description])))

(defn create-cards-table []
	(println "Creating cards table")
	(sql/with-connection
		db
		(sql/create-table
			:cards
			[:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
			[:name "TEXT"]
			[:cardid "TEXT"]
			[:color "TEXT"]
			[:cardset "TEXT"]
			[:rarity "TEXT"]
			[:artist "TEXT"])))

(defn save-card [name cardid color cardset rarity artist]
	(println (str "Saving card " name))
	(sql/with-connection
		db
		(sql/insert-values
			:cards
			[:name :cardid :color :cardset :rarity :artist]
			[name cardid color cardset rarity artist])))

(defn populate-cards []
	(println "Populating cards")
	(doall (for [set (fetcher/select-sets)]
		(doall (for [card (fetcher/select-cards set)]
			(save-card 
					(get card :name) 
					(get card :id) 
					(get card :color) 
					(get card :set) 
					(get card :rarity)
					(get card :artist)))))))

(defn populate-sets []
	(println "Populating sets")
	(doall (for [set (fetcher/select-sets)]
		(save-set set ""))))

(defn populate-database []
	(create-sets-table)
	(populate-sets)
	(create-cards-table)
	(populate-cards))

(defn init-database []
	(if-not (.exists (java.io.File. "./db.sq3"))
		(populate-database)))
