(ns district.ui.mobile.test-runner
  (:require
   [cljs.spec.alpha :as s]
   [doo.runner :refer-macros [doo-tests]]
   [district.ui.mobile.core-test]
   ))

(s/check-asserts true)

(enable-console-print!)

(doo-tests
 'district.ui.mobile.core-test)
