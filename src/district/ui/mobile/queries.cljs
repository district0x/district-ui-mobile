(ns district.ui.mobile.queries
  (:require
   [district.ui.mobile.browser :as browser]))


(defn assoc-mobile-settings
  [db {:keys [force-mobile-device] :as opts}]
  (assoc-in
   db
   [:district.ui.mobile]
   (with-redefs [browser/android? (if (contains? #{:android, true} force-mobile-device)
                                     (constantly true)
                                     browser/android?)
                 browser/ios? (if (= :ios force-mobile-device)
                                (constantly true)
                                browser/ios?)]
     {:android? (browser/android?)
      :ios? (browser/ios?)
      :coinbase-compatible? (browser/coinbase-compatible?)})))
