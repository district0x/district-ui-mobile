(ns district.ui.mobile.subs
  (:require
   [re-frame.core :refer [reg-sub]]))


(reg-sub ::android? #(get-in % [:district.ui.mobile :android?]))
(reg-sub ::ios? #(get-in % [:district.ui.mobile :ios?]))
(reg-sub ::coinbase-compatible? #(get-in %[:district.ui.mobile :coinbase-compatible?]))
