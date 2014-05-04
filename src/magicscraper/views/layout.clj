(ns magicscraper.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to magicscraper"]
     (include-css "/css/screen.css")]
    [:body body]))
