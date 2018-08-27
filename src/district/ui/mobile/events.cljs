(ns district.ui.mobile.events
  (:require
   [district.ui.mobile.browser :as browser]
   [re-frame.core :refer [reg-event-fx trim-v]]))


(def interceptors [trim-v])


(defn assoc-mobile-settings [db {:keys [force-mobile-device] :as opts}]
  (-> db
      (update-in
       [:district.ui.mobile]
       merge
       {:android? (or (contains? #{:android, true} force-mobile-device) (browser/android?))
        :ios? (or (= :ios force-mobile-device) browser/ios?)
        :coinbase-compatible? (browser/coinbase-compatible?)})))


(reg-event-fx
 ::start
 interceptors
 (fn [{:keys [:db]} [{:keys [force-mobile-device] :as opts}]]
   {:db (assoc-mobile-settings db opts)}))


(reg-event-fx
 ::stop
 interceptors
 (fn [{:keys [:db]}]))
   
