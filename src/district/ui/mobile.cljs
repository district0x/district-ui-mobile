(ns district.ui.mobile
  (:require
   [cljs.spec.alpha :as s]
   [district.ui.mobile.events :as events]
   [mount.core :as mount :refer [defstate]]
   [re-frame.core :refer [dispatch-sync]]))

(declare start)
(declare stop)
(defstate mobile
  :start (start (:mobile (mount/args)))
  :stop (stop))


(def default-options
  {:force-mobile-device false ;; true (android), :android, :ios
   })


(defn start [opts]
  (dispatch-sync [::events/start (merge default-options opts)])
  opts)


(defn stop []
  (dispatch-sync [::events/stop]))
