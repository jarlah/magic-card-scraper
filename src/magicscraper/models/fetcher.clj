(ns magicscraper.models.fetcher
	(:require [net.cgrand.enlive-html :as html]))

;; the basic stuff needed
(def baseurl "http://gatherer.wizards.com/Pages/Search/Default.aspx?output=checklist&action=advanced&set=")
(def query "|[\"Theros\"]|[\"Journey+into+Nyx\"]|[\"Born+of+the+Gods\"]")

;; equivalent to jquery selectors
(def nameSelector [:td.name :a.nameLink html/text])
(def colorSelector [:td.color html/text])
(def raritySelector [:td.rarity html/text])
(def setExtractor [:td.set html/text])

(defn fetch-sets [query]
	(html/html-resource (java.net.URL. (str baseurl query))))

(defn extract-value [card selector]
	(first (html/select card selector)))

(defn extract-card 
	"Converts a cardItem (a table row) into a map" 
	[cardItem]
	{ :name (extract-value cardItem nameSelector) 
	  :color (extract-value cardItem colorSelector) 
	  :rarity (extract-value cardItem raritySelector)
	  :set (extract-value cardItem setExtractor)
	  :id "n/a"})

(defn select-cards [] 
	(map extract-card (html/select (fetch-sets query) [:table.checklist :tr.cardItem])))