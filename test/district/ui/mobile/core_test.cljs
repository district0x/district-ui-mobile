(ns district.ui.mobile.core-test
  (:require
    [cljs.test :refer [deftest is testing run-tests async use-fixtures]]
    [day8.re-frame.test :refer [run-test-async wait-for run-test-sync]]
    [district.ui.mobile.events :as events]
    [district.ui.mobile.subs :as subs]
    [district.ui.mobile]
    [mount.core :as mount]
    [re-frame.core :refer [reg-event-fx dispatch-sync subscribe reg-cofx reg-fx dispatch]]))

(use-fixtures :each {:after #(mount/stop)})


(deftest check-desktop-machine
  (run-test-sync

   ;; Initialize
   (mount/start (mount/with-args {:mobile {:force-mobile-device false}}))

   ;; Check Subscriptions
   (let [android? (subscribe [::subs/android?])
         ios? (subscribe [::subs/ios?])
         coinbase-compatible? (subscribe [::subs/coinbase-compatible?])]
     (is (false? @android?))
     (is (false? @ios?))
     (is (false? @coinbase-compatible?)))))


(deftest check-android-machine-1
  (run-test-sync

   ;; Initialize
   (mount/start (mount/with-args {:mobile {:force-mobile-device true}}))

   ;; Check Subscriptions
   (let [android? (subscribe [::subs/android?])
         ios? (subscribe [::subs/ios?])
         coinbase-compatible? (subscribe [::subs/coinbase-compatible?])]
     (is (true? @android?))
     (is (false? @ios?))
     (is (true? @coinbase-compatible?)))))


(deftest check-android-machine-2
  (run-test-sync

   ;; Initialize
   (mount/start (mount/with-args {:mobile {:force-mobile-device :android}}))

   ;; Check Subscriptions
   (let [android? (subscribe [::subs/android?])
         ios? (subscribe [::subs/ios?])
         coinbase-compatible? (subscribe [::subs/coinbase-compatible?])]
     (is (true? @android?))
     (is (false? @ios?))
     (is (true? @coinbase-compatible?)))))


(deftest check-ios-machine
  (run-test-sync

   ;; Initialize
   (mount/start (mount/with-args {:mobile {:force-mobile-device :ios}}))

   ;; Check Subscriptions
   (let [android? (subscribe [::subs/android?])
         ios? (subscribe [::subs/ios?])
         coinbase-compatible? (subscribe [::subs/coinbase-compatible?])]
     (is (false? @android?))
     (is (true? @ios?))
     (is (true? @coinbase-compatible?)))))
