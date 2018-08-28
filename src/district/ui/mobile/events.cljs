(ns district.ui.mobile.events
  (:require
   [district.ui.mobile.browser :as browser]
   [district.ui.mobile.queries :as queries]
   [re-frame.core :refer [reg-event-fx trim-v]]))


(def interceptors [trim-v])


(reg-event-fx
 ::start
 interceptors
 (fn [{:keys [:db]} [{:keys [force-mobile-device] :as opts}]]
   {:db (queries/assoc-mobile-settings db opts)}))


(reg-event-fx
 ::stop
 interceptors
 (fn [{:keys [:db]}]))
