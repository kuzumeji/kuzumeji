// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
/**
 * アプリケーション層
 * <dl>
 * <dt>表明保証
 * <dd>そのタスクを実行するためにアプリケーションに必要とされるコードであること。それは定義する、またはユースケースにより定義されるものであること。
 * <dt>特記など
 * <dd>それはコード行数の点で大きくなる可能性はあるが、ドメインのビジネスロジックに関する知識の点で薄くなる。これは実際のタスクを実行するために、ドメイン層のオブジェクトを調整します。この層は、短時間トランザクション(spanning transactions)、セキュリティチェックと高次ロギングに適しています。
 * </dl>
 * @author nilcy
 */
package net.java.cargotracker.application;