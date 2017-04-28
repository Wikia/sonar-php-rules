<?php
class FooBar {
    public function dalej() {
        wfMessage( 'foo' )->plain(); // NOK
        $this->msg( 'bar' )->plain(); // NOK

        wfMessage( 'foo' )->text();
        $this->msg( 'escaped' )->escaped();
    }
}