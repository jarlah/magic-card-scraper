(ns magicscraper.models.fetcher
	(:require [net.cgrand.enlive-html :as html]))

;; the basic stuff needed
(def baseurl "http://gatherer.wizards.com/Pages/Search/Default.aspx?output=checklist&action=advanced&set=")
(def query "|[\"Theros\"]|[\"Journey+into+Nyx\"]|[\"Born+of+the+Gods\"]")

;; equivalent to jquery selectors
(def nameSelector [:td.name :a.nameLink html/text])
(def linkSelector [:td.name :a.nameLink])
(def colorSelector [:td.color html/text])
(def raritySelector [:td.rarity html/text])
(def setSelector [:td.set html/text])
(def artistSelector [:td.artist html/text])

(defn fetch-sets "Fetches the sets, provided by the query parameter, from gatherer" 
	[query]
	(html/html-resource (java.net.URL. (str baseurl query))))

(defn extract-value "Returns the first value in a vector from a html node selection" 
	[node selector]
	(first (html/select node selector)))

;; TODO this has to be possible to achieve with enlive
;; Currently just calls extract-value and gets href from attributes
;; then it splits the href value on "=" and takes the last argument which is multiverseid
(defn extract-id-hack "Silly hack to achieve something that could have been done with enlive" 
	[cardItem]
	(last (clojure.string/split ((comp :href :attrs)(extract-value cardItem linkSelector)) #"=")))

;; Did i mention that creating maps in Clojure is pretty ugly? Maybe its me that is not used it.
(defn extract-card "Creates a map of the extracted attributes of a provided magic card" 
	[cardItem]
	{:name 		(extract-value cardItem nameSelector) 
	 :color 	(extract-value cardItem colorSelector) 
	 :rarity 	(extract-value cardItem raritySelector)
	 :set 		(extract-value cardItem setSelector)
	 :id 		(extract-id-hack cardItem)
	 :artist 	(extract-value cardItem artistSelector)})

(defn select-cards "Simpply fetches the search result and selects the card" 
	[] 
	(pmap extract-card (html/select (fetch-sets query) [:table.checklist :tr.cardItem])))