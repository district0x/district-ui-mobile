(ns district.ui.mobile.browser
  "Functions for determining the type of mobile browser"
  (:require
   [clojure.string :as str]))


(defn supported-browser?
  []
  (try
    (boolean js/navigator)
    (catch js/Error e
      false)))


(defn ios?
  "Determines if the browser is on an iPhone or an iPad"
  []
  (and
   (supported-browser?)
   (let [user-agent (aget js/navigator "userAgent")]
     (or
      (str/includes? user-agent "iPhone")
      (str/includes? user-agent "iPad")))))


(defn android?
  "Determines if the browser is on a mobile android device."
  []
  (and
   (supported-browser?)
   (-> js/navigator (aget "userAgent") (str/includes? "Android"))))


(defn coinbase-compatible?
  "Determines whether it is a coinbase compatible mobile device."
  []
  (or (ios?) (android?)))


(defn device-type []
  (cond
   (ios?) ::iphone
   (android?) ::android
   :else ::unknown))
